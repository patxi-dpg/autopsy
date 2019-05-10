/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.casemodule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FileUtils;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataSourceProcessorProgressMonitor;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataSourceProcessorCallback;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataSourceProcessor;
import org.sleuthkit.autopsy.events.AutopsyEventException;

/**
 * A Logical Imager data source processor that implements the DataSourceProcessor service
 * provider interface to allow integration with the add data source wizard. It
 * also provides a run method overload to allow it to be used independently of
 * the wizard.
 */
@ServiceProviders(value={
    @ServiceProvider(service=DataSourceProcessor.class)}
)
public class LogicalImagerDSProcessor implements DataSourceProcessor {

    private static final String LOGICAL_IMAGER_DIR = "LogicalImager"; //NON-NLS
    private final LogicalImagerPanel configPanel;
    
    private AddImageTask addImageTask;
    
    /*
     * Constructs a Logical Imager data source processor that implements the
     * DataSourceProcessor service provider interface to allow integration with
     * the add data source wizard. It also provides a run method overload to
     * allow it to be used independently of the wizard.
     */
    public LogicalImagerDSProcessor() {
        configPanel = LogicalImagerPanel.createInstance(LogicalImagerDSProcessor.class.getName());
    }

    /**
     * Gets a string that describes the type of data sources this processor is
     * able to add to the case database. The string is suitable for display in a
     * type selection UI component (e.g., a combo box).
     *
     * @return A data source type display string for this data source processor.
     */
    @Messages({"LogicalImagerDSProcessor.dataSourceType=Autopsy Imager"})
    public static String getType() {
        return Bundle.LogicalImagerDSProcessor_dataSourceType();
    }

    /**
     * Gets a string that describes the type of data sources this processor is
     * able to add to the case database. The string is suitable for display in a
     * type selection UI component (e.g., a combo box).
     *
     * @return A data source type display string for this data source processor.
     */
    @Override
    public String getDataSourceType() {
        return Bundle.LogicalImagerDSProcessor_dataSourceType();
    }

    /**
     * Gets the panel that allows a user to select a data source and do any
     * configuration required by the data source. The panel is less than 544
     * pixels wide and less than 173 pixels high.
     *
     * @return A selection and configuration panel for this data source
     *         processor.
     */
    @Override
    public JPanel getPanel() {
        configPanel.reset();
        return configPanel;
    }

    /**
     * Indicates whether the settings in the selection and configuration panel
     * are valid and complete.
     *
     * @return True if the settings are valid and complete and the processor is
     *         ready to have its run method called, false otherwise.
     */
    @Override
    public boolean isPanelValid() {
        return configPanel.validatePanel();
    }

    /**
     * Adds a data source to the case database using a background task in a
     * separate thread and the settings provided by the selection and
     * configuration panel. Returns as soon as the background task is started.
     * The background task uses a callback object to signal task completion and
     * return results.
     *
     * This method should not be called unless isPanelValid returns true.
     *
     * @param progressMonitor Progress monitor that will be used by the
     *                        background task to report progress.
     * @param callback        Callback that will be used by the background task
     *                        to return results.
     */
    @Override
    public void run(DataSourceProcessorProgressMonitor progressMonitor, DataSourceProcessorCallback callback) {
        configPanel.storeSettings();
        Path imagePath = configPanel.getImagePath();
        if (!imagePath.toFile().exists()) {
            // TODO: Better ways to detect ejected USB drive?
            String msg = imagePath.toString() + " not found.\nUSB drive has been ejected.";
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create the LogicalImager directory under ModuleDirectory
        String moduleDirectory = Case.getCurrentCase().getModuleDirectory();
        File logicalImagerDir = Paths.get(moduleDirectory, LOGICAL_IMAGER_DIR).toFile();
        if (!logicalImagerDir.exists()) {
            if (!logicalImagerDir.mkdir()) {
                // create failed
                String msg = "Fail to create directory " + logicalImagerDir;
                JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String imagePathParent = imagePath.getParent().toFile().getName();
        File dest = Paths.get(logicalImagerDir.toString(), imagePathParent).toFile();
        if (dest.exists()) {
            // directory already exists
            String msg = "Directory " + dest.toString() + " already exists";
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File src = imagePath.getParent().toFile();
        try {
            configPanel.setMessageLabel("Copying " + src.toString() + " directory to " + dest.toString());
            FileUtils.copyDirectory(src, dest);
            configPanel.setMessageLabel("");
        } catch (IOException ex) {
            // Copy directory failed
            String msg = "Failed to copy directory " + imagePath.getParent().toString() + " to " + dest.toString() ;
            JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String deviceId = UUID.randomUUID().toString();
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        boolean ignoreFatOrphanFiles = false;
        run(deviceId, imagePath.toString(), 0, timeZone, ignoreFatOrphanFiles, null, null, null, progressMonitor, callback);
    }
    
    /**
     * Adds a "Logical Imager" data source to the case database using a background task in
     * a separate thread and the given settings instead of those provided by the
     * selection and configuration panel. Returns as soon as the background task
     * is started and uses the callback object to signal task completion and
     * return results.
     *
     * @param deviceId             An ASCII-printable identifier for the device
     *                             associated with the data source that is
     *                             intended to be unique across multiple cases
     *                             (e.g., a UUID).
     * @param imageFilePath        Path to the image file.
     * @param timeZone             The time zone to use when processing dates
     *                             and times for the image, obtained from
     *                             java.util.TimeZone.getID.
     * @param chunkSize            The maximum size of each chunk of the raw
     *                             data source as it is divided up into virtual
     *                             unallocated space files.
     * @param progressMonitor      Progress monitor for reporting progress
     *                             during processing.
     * @param callback             Callback to call when processing is done.
     */
    private void run(String deviceId, String imagePath, int sectorSize, String timeZone, boolean ignoreFatOrphanFiles, String md5, String sha1, String sha256, DataSourceProcessorProgressMonitor progressMonitor, DataSourceProcessorCallback callback) {
        addImageTask = new AddImageTask(deviceId, imagePath, sectorSize, timeZone, ignoreFatOrphanFiles, md5, sha1, sha256, null, progressMonitor, callback);
        new Thread(addImageTask).start();
    }

    @Override
    public void cancel() {
    }

    /**
     * Resets the selection and configuration panel for this data source
     * processor.
     */
    @Override
    public void reset() {
        configPanel.reset();
    }

}
