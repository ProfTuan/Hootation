/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment.model;

import edu.uthouston.sbmi.hootation.llmenrichment.util.LLMConfiguration;


/**
 *
 * @author mac
 */
public class LLMModelParameters {
    private int num_threads;
    private int layers;
    private String fileModelPath;
    
    public LLMModelParameters(){
        LLMConfiguration config = LLMConfiguration.getInstance();
        
        num_threads = config.getNumThreads();
        layers = config.getLayers();
        fileModelPath = config.getModelFilePath();
        
    }
    
    public int NThreads(){
        return num_threads;
    }
    
    public void setNThreads(int value){
        num_threads = value;
    }
    
    public int NGpuLayers (){
        return layers;
    }
    
    public void setNGpuLayers(int value){
        layers = value;
    }
    
    public String getfileModelPath(){
        return this.fileModelPath;
    }
    
    public void setFileModelPath(String value){
        fileModelPath = value;
    }
    
}
