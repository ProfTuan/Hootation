/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment.model;

import de.kherud.llama.args.MiroStat;

/**
 *
 * @author mac
 */
public class LLMParameters  {
   
    private static LLMParameters INSTANCE = null;
    
    //inference parameters
    private LLMInferenceParameters inference_parameters;
    private LLMModelParameters model_parameters;
    
    private LLMParameters(){
        
        inference_parameters = new LLMInferenceParameters();
        model_parameters = new LLMModelParameters();
        
        
        
    }
    
    public int getNPredict(){
        return inference_parameters.getNumPredict();
    }
    
    public void setNPredict(int value){
        inference_parameters.setNumPredict(value);
    }
    
    public int getNThreads(){
        return model_parameters.NThreads();
    }
    
    public void setNThreads(int threads){
        model_parameters.setNThreads(threads);
    }
    
    public int getNGpuLayers(){
        return model_parameters.NGpuLayers();
        
        
    }
    
    public void setNGpuLayers(int layers){
        model_parameters.setNGpuLayers(layers);
    }
    
    public void setTemperature(float temp){
        inference_parameters.setTemperature(temp);
    }
    
    public float getTemperature(){
        return inference_parameters.getTemperature();
    }
    
    public void setShouldPenalize(boolean penalize){
        inference_parameters.setShouldPenalize(penalize);
    }
    
    public boolean getShouldPenalize(){
        return inference_parameters.getShouldPenalize();
    }
    
    public String getFileModelPath(){
        return model_parameters.getfileModelPath(); 
    }
    
    public void setFileModelPath(String path){
        model_parameters.setFileModelPath(path);
    }
    
    public void setMiroStatVersion(MiroStat version){
        
        inference_parameters.setMiroStatVersion(version);
        
    }
    
    public MiroStat getMiroStatVersion(){
        
        return inference_parameters.getMiroStatVersion();
    }
    
    public static LLMParameters getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LLMParameters();
        }
        
        return INSTANCE;
    }
    
    
    
    
}
