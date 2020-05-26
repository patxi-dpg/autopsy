/*
 * Central Repository
 *
 * Copyright 2020 Basis Technology Corp.
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
package org.sleuthkit.autopsy.centralrepository.persona;

import java.awt.Component;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.centralrepository.datamodel.Persona;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * Configuration dialog for editing or creating a persona
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
public class PersonaDetailsDialog extends JDialog {
    private static final Logger logger = Logger.getLogger(PersonaDetailsDialog.class.getName());

    private static final long serialVersionUID = 1L;
    
    private final Component parent;
    private final PersonaDetailsMode mode;

    @NbBundle.Messages({
        "PersonaDetailsDialogCreateTitle=Create Persona",
        "PersonaDetailsDialogEditTitle=Edit Persona",})
    PersonaDetailsDialog(Component parent, PersonaDetailsMode mode, Persona persona) {
        super((JFrame) WindowManager.getDefault().getMainWindow(),
                mode == PersonaDetailsMode.CREATE ? 
                        Bundle.PersonaDetailsDialogCreateTitle() : 
                        Bundle.PersonaDetailsDialogEditTitle(),
                false);
        this.parent = parent;
        this.mode = mode;

        initComponents();
        
        pdp.setMode(parent, mode, persona);
        
        display();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();
        pdp = new org.sleuthkit.autopsy.centralrepository.persona.PersonaDetailsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        org.openide.awt.Mnemonics.setLocalizedText(cancelBtn, org.openide.util.NbBundle.getMessage(PersonaDetailsDialog.class, "PersonaDetailsDialog.cancelBtn.text")); // NOI18N
        cancelBtn.setMaximumSize(new java.awt.Dimension(79, 23));
        cancelBtn.setMinimumSize(new java.awt.Dimension(79, 23));
        cancelBtn.setPreferredSize(new java.awt.Dimension(79, 23));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(okBtn, org.openide.util.NbBundle.getMessage(PersonaDetailsDialog.class, "PersonaDetailsDialog.okBtn.text")); // NOI18N
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pdp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pdp, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void display() {
        this.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        setVisible(true);
    }

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        switch (mode) {
            case CREATE:
                // todo implement
                break;
            case EDIT:
                // todo implement
                break;
            default:
                logger.log(Level.SEVERE, "Unsupported mode: {0}", mode);
        }
        dispose();
    }//GEN-LAST:event_okBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton okBtn;
    private org.sleuthkit.autopsy.centralrepository.persona.PersonaDetailsPanel pdp;
    // End of variables declaration//GEN-END:variables
}
