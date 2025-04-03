/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.uthouston.sbmi.hootation.ui;


import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import edu.uthouston.sbmi.hootation.GenerateStatements;
import edu.utmb.semantic.llmenrichment.LLMAdapter;

import edu.utmb.semantic.llmenrichment.util.LLMConfiguration;
import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mac
 */
public class HootationUI extends javax.swing.JFrame {
    
    private boolean doFactChecking = false;
    private boolean doRefinement = false;
    
    private  DefaultListModel llm_list = new DefaultListModel();
    private Map<String, String> llmList;
    private String llmModelPath = "";
    
    private String saveLLMModelPath = "";
    /**
     * Creates new form HootationUI
     */
    public HootationUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.populateLLMDownloadList();
        
        //hide the download LLM
        this.tabbedPane.remove(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outputOptions = new javax.swing.ButtonGroup();
        modeldownloadOption = new javax.swing.ButtonGroup();
        tabbedPane = new javax.swing.JTabbedPane();
        panelHootation = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selectButtonFromMain = new javax.swing.JButton();
        pathTextFromMain = new javax.swing.JTextField();
        saveToButtonFromMain = new javax.swing.JButton();
        pathTextExportFromMain = new javax.swing.JTextField();
        csvOption = new javax.swing.JRadioButton();
        excelOption = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        btnGenerateTranslation = new javax.swing.JButton();
        ckLLM = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        llmPanel = new javax.swing.JPanel();
        ckLLMRefinement = new javax.swing.JCheckBox();
        ckLLMFactChecking = new javax.swing.JCheckBox();
        panelLLMDownload = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btSaveModelPath = new javax.swing.JButton();
        txtSaveLLMModelLocation = new javax.swing.JTextField();
        btnLLMDownload = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstLLM = new javax.swing.JList<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        panelLLMConfiguration = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txtModelPath = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGPULayer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThreads = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPredictionLength = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputPanel = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabbedPaneFocusGained(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("Select Ontology Artifact");

        selectButtonFromMain.setText("Select File");
        selectButtonFromMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonFromMainActionPerformed(evt);
            }
        });

        pathTextFromMain.setEditable(false);
        pathTextFromMain.setText("[Path to ontology file]");

        saveToButtonFromMain.setText("Save To");
        saveToButtonFromMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToButtonFromMainActionPerformed(evt);
            }
        });

        pathTextExportFromMain.setEditable(false);
        pathTextExportFromMain.setText("[Path to export file]");

        outputOptions.add(csvOption);
        csvOption.setSelected(true);
        csvOption.setText("CSV (.csv)");
        csvOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvOptionActionPerformed(evt);
            }
        });

        outputOptions.add(excelOption);
        excelOption.setText("Excel (.xlsx)");
        excelOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelOptionActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setText("Set up output export (File format and file name)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(saveToButtonFromMain, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pathTextExportFromMain))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(csvOption)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(excelOption))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(selectButtonFromMain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pathTextFromMain)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButtonFromMain)
                    .addComponent(pathTextFromMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(csvOption)
                    .addComponent(excelOption))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveToButtonFromMain)
                    .addComponent(pathTextExportFromMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnGenerateTranslation.setText("Generate Translation");
        btnGenerateTranslation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateTranslationActionPerformed(evt);
            }
        });

        ckLLM.setText("LLM enhancement (Optional, Experimental)");
        ckLLM.setName("ckLLM"); // NOI18N
        ckLLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLLMActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 2, 13)); // NOI18N
        jLabel7.setText("N.B. Ensure you downloaded a LLM model. Refer to LLM Configuration for to set parameters");

        llmPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ckLLMRefinement.setText("Provide LLM refinement of the translation");
        ckLLMRefinement.setEnabled(false);
        ckLLMRefinement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLLMRefinementActionPerformed(evt);
            }
        });

        ckLLMFactChecking.setText("Provide basic fact checking of the statements");
        ckLLMFactChecking.setEnabled(false);
        ckLLMFactChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckLLMFactCheckingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout llmPanelLayout = new javax.swing.GroupLayout(llmPanel);
        llmPanel.setLayout(llmPanelLayout);
        llmPanelLayout.setHorizontalGroup(
            llmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(llmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckLLMRefinement)
                    .addComponent(ckLLMFactChecking))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        llmPanelLayout.setVerticalGroup(
            llmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ckLLMRefinement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckLLMFactChecking)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelHootationLayout = new javax.swing.GroupLayout(panelHootation);
        panelHootation.setLayout(panelHootationLayout);
        panelHootationLayout.setHorizontalGroup(
            panelHootationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHootationLayout.createSequentialGroup()
                .addGroup(panelHootationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHootationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelHootationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ckLLM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelHootationLayout.createSequentialGroup()
                                .addComponent(btnGenerateTranslation)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)))
                    .addGroup(panelHootationLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(llmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelHootationLayout.setVerticalGroup(
            panelHootationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHootationLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckLLM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(llmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerateTranslation)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Hootation", panelHootation);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Choice of the Model to Download:");

        btSaveModelPath.setText("Save Model To");
        btSaveModelPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelSavePathActionPerformed(evt);
            }
        });

        txtSaveLLMModelLocation.setEditable(false);

        btnLLMDownload.setText("Download");
        btnLLMDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLLMDownloadActionPerformed(evt);
            }
        });

        lstLLM.setModel(llm_list);
        jScrollPane2.setViewportView(lstLLM);

        jButton9.setText("Add URL for another model");

        jButton10.setText("Remove URL for a model");

        javax.swing.GroupLayout panelLLMDownloadLayout = new javax.swing.GroupLayout(panelLLMDownload);
        panelLLMDownload.setLayout(panelLLMDownloadLayout);
        panelLLMDownloadLayout.setHorizontalGroup(
            panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel2)
                    .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                        .addGroup(panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLLMDownload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSaveModelPath, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSaveLLMModelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        panelLLMDownloadLayout.setVerticalGroup(
            panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLLMDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSaveModelPath)
                            .addComponent(txtSaveLLMModelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLLMDownloadLayout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addGap(12, 12, 12)
                .addComponent(btnLLMDownload)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        tabbedPane.addTab("LLM Download", panelLLMDownload);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("Model Parameter Set Up:");

        jButton4.setText("Select Model");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtModelPath.setEditable(false);
        txtModelPath.setText("[Selected Model Path]");

        jLabel4.setText("GPU Layers:");

        txtGPULayer.setText("[Enter]");

        jLabel5.setText("Thread Number:");

        txtThreads.setText("[Enter]");

        jLabel6.setText("Prediction Length:");

        txtPredictionLength.setText("[Enter]");

        javax.swing.GroupLayout panelLLMConfigurationLayout = new javax.swing.GroupLayout(panelLLMConfiguration);
        panelLLMConfiguration.setLayout(panelLLMConfigurationLayout);
        panelLLMConfigurationLayout.setHorizontalGroup(
            panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLLMConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelLLMConfigurationLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txtModelPath, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLLMConfigurationLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGPULayer, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPredictionLength, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(txtThreads)))))
                .addContainerGap(288, Short.MAX_VALUE))
        );
        panelLLMConfigurationLayout.setVerticalGroup(
            panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLLMConfigurationLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(txtModelPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGPULayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLLMConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPredictionLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        tabbedPane.addTab("LLM Configuration", panelLLMConfiguration);

        outputPanel.setColumns(20);
        outputPanel.setRows(5);
        jScrollPane1.setViewportView(outputPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void excelOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelOptionActionPerformed
        // TODO add your handling code here:
        String text = this.pathTextExportFromMain.getText();
        text = (String) text.substring(0, text.lastIndexOf(".")-1);
        
        text = text.concat(".xlsx");
        
        this.pathTextExportFromMain.setText(text);
        
    }//GEN-LAST:event_excelOptionActionPerformed

    
    public String getUserSelectedLLMDirectory(){
        return this.saveLLMModelPath;
    }
    
    public void setUserSelectedLLMDirectory(String path){
        this.saveLLMModelPath = path;
    }
    
    public String getLLMModelPath(){
        return this.llmModelPath;
    }
    
    public void setLLMModelPath(String value){
        this.llmModelPath = value;
        
        LLMConfiguration.getInstance().setModelFilePath(value);
    }
    
    private void selectButtonFromMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonFromMainActionPerformed
        // TODO add your handling code here:
        
        FileDialog filedialog = new FileDialog(this, "Choose a location to save", FileDialog.LOAD);
        filedialog.setVisible(true);
        String filePath = filedialog.getDirectory()+ filedialog.getFile();
       
        System.out.println(filePath);
        
        this.pathTextFromMain.setText(filePath);
        
        this.printToConsole("Location selected: " + filePath, Color.BLUE);
        
    }//GEN-LAST:event_selectButtonFromMainActionPerformed

    private void saveToButtonFromMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToButtonFromMainActionPerformed
        // TODO add your handling code here:
        
        FileDialog filedialog = new FileDialog(this, "Choose a location to save", FileDialog.SAVE);
        filedialog.setVisible(true);
        filedialog.setFile("");
        
        
        String fileName = (filedialog.getFile() != null) ? filedialog.getFile() : "fileName"; 
        
        
        if(csvOption.isSelected()){
            fileName = fileName + ".csv";
        }
        else{
            fileName = fileName + ".xlsx";
        }
        this.printToConsole("File named noted: " + fileName, Color.BLUE );
        
        String savePath = filedialog.getDirectory()+fileName;
        
        this.pathTextExportFromMain.setText(savePath);
        
        
        this.printToConsole("Output path indicated: " + savePath, Color.BLUE);
    }//GEN-LAST:event_saveToButtonFromMainActionPerformed

    private void csvOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvOptionActionPerformed
        // TODO add your handling code here:
        
        String text = this.pathTextExportFromMain.getText();
        text = (String) text.substring(0, text.lastIndexOf(".")-1);
        
        text = text.concat(".csv");
        
        this.pathTextExportFromMain.setText(text);
        
        
    }//GEN-LAST:event_csvOptionActionPerformed

    private void modelSavePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelSavePathActionPerformed
        // TODO add your handling code here:
        
        
        
        FileDialog saveDialogModel = new FileDialog(this, "Choose a location to save the model", FileDialog.SAVE);
        
        //saveDialogModel.setDirectory(llmModelPath);
        saveDialogModel.setVisible(true);
        
        
        String savePath = saveDialogModel.getDirectory();
        
        this.txtSaveLLMModelLocation.setText(savePath);
        this.setUserSelectedLLMDirectory(savePath);
        
    }//GEN-LAST:event_modelSavePathActionPerformed

    private void btnGenerateTranslationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateTranslationActionPerformed
        // TODO add your handling code here:
        
        this.printToConsole("Generating statements... \n", Color.BLUE);
        
     
        //get llm parameters
        if(this.ckLLM.isSelected()){
            
            if(this.ckLLMRefinement.isSelected() && this.ckLLMRefinement.isEnabled()){
                this.printToConsole("User specified LLM refinement of sentences", Color.BLUE);
            }
            
            if(this.ckLLMFactChecking.isSelected() && this.ckLLMFactChecking.isEnabled()){
                this.printToConsole("User specified fact checking of statements", Color.BLUE);
            }
            
            this.printToConsole("Getting LLM configuration...", Color.BLUE);
            this.getUserLLMParameters();
        }
        
        
        GenerateStatementProcess gs_process = new GenerateStatementProcess();
        gs_process.setParent(this);
        gs_process.start();
        
        
       
    }//GEN-LAST:event_btnGenerateTranslationActionPerformed

    class GenerateStatementProcess extends Thread{
        
        private HootationUI gui = null;
        
        public void setParent(HootationUI _parent){
            this.gui = _parent;
        }
        
        @Override
        public void run(){
            GenerateStatements gs = GenerateStatements.getInstance();
            gs.generateStatementsFromAxioms(new File(gui.pathTextFromMain.getText()), this.gui);
            
            if(gui.csvOption.isSelected()){
                gs.outputAsCSVFile(gui.pathTextExportFromMain.getText());
            }
            else{
                gs.outputAsExcel(gui.pathTextExportFromMain.getText());
            }
            
            //JOptionPane.showMessageDialog(gui, "Generation complelted");
            
            
            gui.printToConsole("Generation is completed", Color.BLUE);
        }
        
    }
    
    public void printToConsole(String text, Color color){
        
        this.outputPanel.setForeground(color);
        
        this.outputPanel.append("\n" + text);
        
    }
    
    
    
    public boolean performFactChecking(){
        return doFactChecking;
    }
    
    public boolean performRefinement(){
        return doRefinement;
    }
    
    private void ckLLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLLMActionPerformed
        // TODO add your handling code here:
        
        if(ckLLM.isSelected()){
            llmPanel.setEnabled(true);
            this.ckLLMFactChecking.setEnabled(true);
            this.ckLLMRefinement.setEnabled(true);
            
            
        }
        else{
            llmPanel.setEnabled(false);
            this.ckLLMFactChecking.setEnabled(false);
            this.ckLLMRefinement.setEnabled(false);
        }
        
    }//GEN-LAST:event_ckLLMActionPerformed

    
    private void getUserLLMParameters(){
        LLMConfiguration llm_config = LLMConfiguration.getInstance();
        
        
        llm_config.setLayers(Integer.parseInt(this.txtGPULayer.getText()));
        llm_config.setNumThread(Integer.parseInt(txtThreads.getText()));
        llm_config.setPredictNumber(Integer.parseInt(txtPredictionLength.getText()));
        llm_config.setModelFilePath(this.txtModelPath.getText());
        
    }
    
    private void populateLLMDownloadList(){
        
        LLMConfiguration llm_config = LLMConfiguration.getInstance();

        llmList = llm_config.collectLLMList();
        
        
        int i=0;
        for(var entry : llmList.entrySet()){
            
            llm_list.insertElementAt(entry.getKey(), i);
            
        }
        
        
        //add LLM parameters
        int numThreads = llm_config.getNumThreads();
        int layers = llm_config.getLayers();
        int predictNumber = llm_config.predictNumber();
        
        this.txtGPULayer.setText(layers +"");
        this.txtPredictionLength.setText(predictNumber + "");
        this.txtThreads.setText(numThreads + "");
 
    }
    
    private void tabbedPaneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabbedPaneFocusGained
        // TODO add your handling code here:
        
        
        
        
    }//GEN-LAST:event_tabbedPaneFocusGained

    private void ckLLMRefinementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLLMRefinementActionPerformed
        // TODO add your handling code here:
        if(ckLLMRefinement.isEnabled()) this.doRefinement = ckLLMRefinement.isSelected();
        
    }//GEN-LAST:event_ckLLMRefinementActionPerformed

    private void ckLLMFactCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckLLMFactCheckingActionPerformed
        // TODO add your handling code here:
        if(ckLLMFactChecking.isEnabled()) this.doFactChecking = ckLLMFactChecking.isSelected();
    }//GEN-LAST:event_ckLLMFactCheckingActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        FileDialog filedialog = new FileDialog(this, "Choose the model", FileDialog.LOAD);
        filedialog.setVisible(true);
        String filePath = filedialog.getDirectory()+ filedialog.getFile();

        
        this.txtModelPath.setText(filePath);
        
        this.printToConsole("Selected model: " + filePath, Color.BLUE);
        
        this.setLLMModelPath(filePath);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnLLMDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLLMDownloadActionPerformed
        // TODO add your handling code here:

        if (!lstLLM.isSelectionEmpty()) {

            System.out.println(lstLLM.getSelectedValue());
            System.out.println(this.llmList.get(lstLLM.getSelectedValue()));

            LLMDownloadProcess download_process = new LLMDownloadProcess();
            download_process.setParent(this);
            download_process.setDownloadURL(this.llmList.get(lstLLM.getSelectedValue()).trim());
            download_process.start();
        } else {
            JOptionPane.showMessageDialog(this, "Select a LLM Model from the list");
        }


    }//GEN-LAST:event_btnLLMDownloadActionPerformed

    class LLMDownloadProcess extends Thread{
        private HootationUI parent = null;
        
        private String llm_url = "";
        
        public void setDownloadURL(String url){
            llm_url = url;
        }
        
        public void setParent(HootationUI _parent){
            parent = _parent;
        }
        
        @Override
        public void run(){
            
            LLMAdapter llm_a = LLMAdapter.getInstance();
            LLMConfiguration llm_config = LLMConfiguration.getInstance();
            
            
            llm_a.retrieveLLMModel(llm_url, parent.getUserSelectedLLMDirectory(), parent.outputPanel);
            
            parent.printToConsole("Download complete", Color.BLUE);
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
         
            
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            
            
            
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HootationUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HootationUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSaveModelPath;
    private javax.swing.JButton btnGenerateTranslation;
    private javax.swing.JButton btnLLMDownload;
    private javax.swing.JCheckBox ckLLM;
    private javax.swing.JCheckBox ckLLMFactChecking;
    private javax.swing.JCheckBox ckLLMRefinement;
    private javax.swing.JRadioButton csvOption;
    private javax.swing.JRadioButton excelOption;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel llmPanel;
    private javax.swing.JList<String> lstLLM;
    private javax.swing.ButtonGroup modeldownloadOption;
    private javax.swing.ButtonGroup outputOptions;
    private javax.swing.JTextArea outputPanel;
    private javax.swing.JPanel panelHootation;
    private javax.swing.JPanel panelLLMConfiguration;
    private javax.swing.JPanel panelLLMDownload;
    private javax.swing.JTextField pathTextExportFromMain;
    private javax.swing.JTextField pathTextFromMain;
    private javax.swing.JButton saveToButtonFromMain;
    private javax.swing.JButton selectButtonFromMain;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txtGPULayer;
    private javax.swing.JTextField txtModelPath;
    private javax.swing.JTextField txtPredictionLength;
    private javax.swing.JTextField txtSaveLLMModelLocation;
    private javax.swing.JTextField txtThreads;
    // End of variables declaration//GEN-END:variables
}
