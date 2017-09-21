/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013-2014 Basis Technology Corp.
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
package org.sleuthkit.autopsy.contentviewers;

import java.awt.Component;
import java.util.logging.Level;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataContentViewer;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.datamodel.BlackboardArtifact;
import org.sleuthkit.datamodel.BlackboardAttribute;
import org.sleuthkit.datamodel.TskCoreException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Shows SMS/MMS/EMail messages
 */
@ServiceProvider(service = DataContentViewer.class, position = 4)
public class MessageContentViewer extends javax.swing.JPanel implements DataContentViewer {

    
    private static final Logger LOGGER = Logger.getLogger(MessageContentViewer.class.getName());
    
    private static final int HDR_TAB_INDEX = 0;
    private static final int TEXT_TAB_INDEX = 1;
    private static final int HTML_TAB_INDEX = 2;
    private static final int RTF_TAB_INDEX = 3;
       
    private BlackboardArtifact artifact;    // Artifact currently being displayed
    
    /**
     * Creates new form MessageContentViewer
     */
    public MessageContentViewer() {
        initComponents();
        customizeComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        envelopePanel = new javax.swing.JPanel();
        fromLabel = new javax.swing.JLabel();
        datetimeText = new javax.swing.JLabel();
        fromText = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        toText = new javax.swing.JLabel();
        ccLabel = new javax.swing.JLabel();
        ccText = new javax.swing.JLabel();
        subjectLabel = new javax.swing.JLabel();
        subjectText = new javax.swing.JLabel();
        directionText = new javax.swing.JLabel();
        msgbodyTabbedPane = new javax.swing.JTabbedPane();
        headersScrollPane = new javax.swing.JScrollPane();
        headersTextArea = new javax.swing.JTextArea();
        textbodyScrollPane = new javax.swing.JScrollPane();
        textbodyTextArea = new javax.swing.JTextArea();
        htmlbodyScrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        htmlbodyTextPane = new javax.swing.JTextPane();
        showImagesToggleButton = new javax.swing.JToggleButton();
        rtfbodyScrollPane = new javax.swing.JScrollPane();
        rtfbodyTextPane = new javax.swing.JTextPane();

        setMinimumSize(new java.awt.Dimension(650, 546));

        envelopePanel.setBackground(new java.awt.Color(204, 204, 204));

        org.openide.awt.Mnemonics.setLocalizedText(fromLabel, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.fromLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(datetimeText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.datetimeText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(fromText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.fromText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(toLabel, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.toLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(toText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.toText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ccLabel, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.ccLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ccText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.ccText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(subjectLabel, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.subjectLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(subjectText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.subjectText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(directionText, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.directionText.text")); // NOI18N

        javax.swing.GroupLayout envelopePanelLayout = new javax.swing.GroupLayout(envelopePanel);
        envelopePanel.setLayout(envelopePanelLayout);
        envelopePanelLayout.setHorizontalGroup(
            envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(envelopePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(envelopePanelLayout.createSequentialGroup()
                        .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromLabel)
                            .addComponent(toLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(envelopePanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(datetimeText))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, envelopePanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(directionText))))
                    .addGroup(envelopePanelLayout.createSequentialGroup()
                        .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(envelopePanelLayout.createSequentialGroup()
                                .addComponent(ccLabel)
                                .addGap(18, 18, 18)
                                .addComponent(ccText))
                            .addGroup(envelopePanelLayout.createSequentialGroup()
                                .addComponent(subjectLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectText, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        envelopePanelLayout.setVerticalGroup(
            envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(envelopePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromLabel)
                    .addComponent(datetimeText)
                    .addComponent(fromText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toLabel)
                    .addComponent(toText)
                    .addComponent(directionText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccLabel)
                    .addComponent(ccText))
                .addGap(18, 18, 18)
                .addGroup(envelopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectLabel)
                    .addComponent(subjectText))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        headersTextArea.setEditable(false);
        headersTextArea.setColumns(20);
        headersTextArea.setRows(5);
        headersScrollPane.setViewportView(headersTextArea);

        msgbodyTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.headersScrollPane.TabConstraints.tabTitle"), headersScrollPane); // NOI18N

        textbodyTextArea.setEditable(false);
        textbodyTextArea.setColumns(20);
        textbodyTextArea.setRows(5);
        textbodyScrollPane.setViewportView(textbodyTextArea);

        msgbodyTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.textbodyScrollPane.TabConstraints.tabTitle"), textbodyScrollPane); // NOI18N

        htmlbodyTextPane.setEditable(false);
        jScrollPane2.setViewportView(htmlbodyTextPane);

        org.openide.awt.Mnemonics.setLocalizedText(showImagesToggleButton, org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.showImagesToggleButton.text")); // NOI18N
        showImagesToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showImagesToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(536, Short.MAX_VALUE)
                .addComponent(showImagesToggleButton)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showImagesToggleButton)
                .addContainerGap(333, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 34, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        htmlbodyScrollPane.setViewportView(jPanel2);

        msgbodyTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.htmlbodyScrollPane.TabConstraints.tabTitle"), htmlbodyScrollPane); // NOI18N

        rtfbodyTextPane.setEditable(false);
        rtfbodyScrollPane.setViewportView(rtfbodyTextPane);

        msgbodyTabbedPane.addTab(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.rtfbodyScrollPane.TabConstraints.tabTitle"), rtfbodyScrollPane); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(envelopePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(msgbodyTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(envelopePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgbodyTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showImagesToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showImagesToggleButtonActionPerformed
       
        try {
            BlackboardAttribute attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_CONTENT_HTML));
            if (attr != null && !attr.getValueString().isEmpty()) {
               
                if (showImagesToggleButton.isSelected()) {
                    showImagesToggleButton.setText(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.showImagesToggleButton.hide.text"));
                   
                    this.htmlbodyTextPane.setText("<html><body>" + attr.getValueString() + "</body></html>");
                }
                else {
                    showImagesToggleButton.setText(org.openide.util.NbBundle.getMessage(MessageContentViewer.class, "MessageContentViewer.showImagesToggleButton.text"));
                    
                    this.htmlbodyTextPane.setText("<html><body>" + cleanseHTML(attr.getValueString()) + "</body></html>");
                }
            }
        } catch (TskCoreException ex) {
            LOGGER.log(Level.WARNING, "Failed to get attributes for email message.", ex); //NON-NLS
        }
    }//GEN-LAST:event_showImagesToggleButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ccLabel;
    private javax.swing.JLabel ccText;
    private javax.swing.JLabel datetimeText;
    private javax.swing.JLabel directionText;
    private javax.swing.JPanel envelopePanel;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel fromText;
    private javax.swing.JScrollPane headersScrollPane;
    private javax.swing.JTextArea headersTextArea;
    private javax.swing.JScrollPane htmlbodyScrollPane;
    private javax.swing.JTextPane htmlbodyTextPane;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane msgbodyTabbedPane;
    private javax.swing.JScrollPane rtfbodyScrollPane;
    private javax.swing.JTextPane rtfbodyTextPane;
    private javax.swing.JToggleButton showImagesToggleButton;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JLabel subjectText;
    private javax.swing.JScrollPane textbodyScrollPane;
    private javax.swing.JTextArea textbodyTextArea;
    private javax.swing.JLabel toLabel;
    private javax.swing.JLabel toText;
    // End of variables declaration//GEN-END:variables

    private void customizeComponents() {
        // do any customizations here
         Utilities.configureTextPaneAsHtml(htmlbodyTextPane);
         Utilities.configureTextPaneAsRtf(rtfbodyTextPane);
         
    }
    
    @Override
    public void setNode(Node node) {
        
        artifact = node.getLookup().lookup(BlackboardArtifact.class);
       
        if (artifact == null) {
            return;
        }
        
        if (artifact.getArtifactTypeID() == BlackboardArtifact.ARTIFACT_TYPE.TSK_MESSAGE.getTypeID()) {
            displayMsg();
        }
        else if (artifact.getArtifactTypeID() == BlackboardArtifact.ARTIFACT_TYPE.TSK_EMAIL_MSG.getTypeID()) {
            displayEmailMsg();
        }
       
    }

    @Override
    public String getTitle() {
        return NbBundle.getMessage(this.getClass(), "MessageContentViewer.title");
    }

    @Override
    public String getToolTip() {
        return NbBundle.getMessage(this.getClass(), "MessageContentViewer.toolTip");
    }

    @Override
    public DataContentViewer createInstance() {
        return new MessageContentViewer();
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void resetComponent() {
        // reset all fields
    }

    @Override
    public boolean isSupported(Node node) {
       BlackboardArtifact artifact = node.getLookup().lookup(BlackboardArtifact.class);
       return ( (artifact != null) 
                && ((artifact.getArtifactTypeID() == BlackboardArtifact.ARTIFACT_TYPE.TSK_EMAIL_MSG.getTypeID())
                || (artifact.getArtifactTypeID() == BlackboardArtifact.ARTIFACT_TYPE.TSK_MESSAGE.getTypeID())));
    }

    @Override
    public int isPreferred(Node node) {
       
        if ( isSupported(node)){
            return 6;
        }
        return 0;
    }
  
    
    private void displayEmailMsg()
    {
        directionText.setVisible(false);
        
        showImagesToggleButton.setVisible(false);
        showImagesToggleButton.setText("Show Images");
        showImagesToggleButton.setSelected(false);
        
        try {
            BlackboardAttribute attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_FROM));
            this.fromText.setText(attr.getValueString());
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_TO));
            if (attr != null) {
                 this.toText.setText(attr.getValueString());
            }
           
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_CC));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.ccText.setVisible(true);
                this.ccText.setText(attr.getValueString());
            }
            else {
                this.ccText.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_SUBJECT));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.subjectText.setVisible(true);
                this.subjectText.setText(attr.getValueString());
            }
            else {
                this.subjectText.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME_RCVD));
            if (attr != null && !attr.getDisplayString().isEmpty()) {
                this.datetimeText.setVisible(true);
                this.datetimeText.setText(attr.getDisplayString());
            }
            else {
                this.datetimeText.setVisible(false);
            }
            
            int selectedTabIndex = -1;
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_CONTENT_PLAIN));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.textbodyTextArea.setVisible(true);
                this.textbodyTextArea.setText(attr.getValueString());
                
                msgbodyTabbedPane.setEnabledAt(TEXT_TAB_INDEX, true);
                selectedTabIndex = TEXT_TAB_INDEX;
            }
            else {
                msgbodyTabbedPane.setEnabledAt(TEXT_TAB_INDEX, false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_CONTENT_HTML));
            if (attr != null && !attr.getValueString().isEmpty()) {
                
                this.showImagesToggleButton.setVisible(true);
                
                
                this.htmlbodyTextPane.setVisible(true);
                this.htmlbodyTextPane.setText("<html><body>" + cleanseHTML(attr.getValueString()) + "</body></html>");
                //this.htmlbodyTextPane.setText(cleanseHTML(attr.getValueString()));
                
                msgbodyTabbedPane.setEnabledAt(HTML_TAB_INDEX, true);
                selectedTabIndex = HTML_TAB_INDEX;
            }
            else {
                msgbodyTabbedPane.setEnabledAt(HTML_TAB_INDEX, false);
                this.htmlbodyTextPane.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_EMAIL_CONTENT_RTF));
            if (attr != null && !attr.getValueString().isEmpty()) {
                
                this.rtfbodyTextPane.setVisible(true);
                this.rtfbodyTextPane.setText(attr.getValueString());
                
                msgbodyTabbedPane.setEnabledAt(RTF_TAB_INDEX, true);
                selectedTabIndex = RTF_TAB_INDEX;
            }
            else {
                msgbodyTabbedPane.setEnabledAt(RTF_TAB_INDEX, false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_HEADERS));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.headersTextArea.setVisible(true);
                this.headersTextArea.setText(attr.getValueString());
                if (selectedTabIndex < 0) {
                     selectedTabIndex = HDR_TAB_INDEX;
                }
            }
            else {
                msgbodyTabbedPane.setEnabledAt(HDR_TAB_INDEX, false);
            }
            
            msgbodyTabbedPane.setSelectedIndex(selectedTabIndex);
        }
        catch (TskCoreException ex) {
            LOGGER.log(Level.WARNING, "Failed to get attributes for email message.", ex); //NON-NLS
        }
    }
    
    private void displayMsg() {
        
        this.ccText.setVisible(false);
        this.showImagesToggleButton.setVisible(false);
        msgbodyTabbedPane.setEnabledAt(HTML_TAB_INDEX, false);
        msgbodyTabbedPane.setEnabledAt(RTF_TAB_INDEX, false);
        msgbodyTabbedPane.setEnabledAt(HDR_TAB_INDEX, false);
         
        try {
            
            BlackboardAttribute attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_FROM));
            if (attr != null) {
                this.fromText.setText(attr.getValueString());
            }else {
                 this.fromText.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO));
            if (attr != null) {
                 this.toText.setText(attr.getValueString());
            }else {
                 this.toText.setVisible(false);
            }
           
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DIRECTION));
            if (attr != null) {
                 this.directionText.setText(attr.getValueString());
            }else {
                 this.directionText.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_SUBJECT));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.subjectText.setVisible(true);
                this.subjectText.setText(attr.getValueString());
            }
            else {
                this.subjectText.setVisible(false);
            }
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME));
            if (attr != null && !attr.getDisplayString().isEmpty()) {
                this.datetimeText.setVisible(true);
                this.datetimeText.setText(attr.getDisplayString());
            }
            else {
                this.datetimeText.setVisible(false);
            }
            
            
            attr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_TEXT));
            if (attr != null && !attr.getValueString().isEmpty()) {
                this.textbodyTextArea.setVisible(true);
                this.textbodyTextArea.setText(attr.getValueString());
                
                msgbodyTabbedPane.setEnabledAt(TEXT_TAB_INDEX, true);
            }
            else {
                msgbodyTabbedPane.setEnabledAt(TEXT_TAB_INDEX, false);
            }
            msgbodyTabbedPane.setSelectedIndex(TEXT_TAB_INDEX);
        }
        catch (TskCoreException ex) {
            LOGGER.log(Level.WARNING, "Failed to get attributes for message.", ex); //NON-NLS
        }
        
    }
    
    /**
     * Cleans out input HTML string
     */
    private String cleanseHTML(String htmlInString)  {
      
        Document doc = Jsoup.parse(htmlInString);
        
        // fix  all img tags
        doc.select("img[src]").forEach((img) -> {
            img.attr("src", "");
        });

        return doc.html();
        
    }
}
