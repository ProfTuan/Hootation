/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment.model;

import de.kherud.llama.args.MiroStat;
import edu.uthouston.sbmi.hootation.llmenrichment.util.LLMConfiguration;

/**
 *
 * @author mac
 */
public class LLMInferenceParameters {
    
    private float temperature;
    private boolean penalize;
    private MiroStat miroStatVersion;
    
    private String stopString;
    
    private int num_predict;
    
    private String prompt;
    
    public LLMInferenceParameters(){
        
        LLMConfiguration config = LLMConfiguration.getInstance();
        
        temperature = config.getTemperature();
        penalize = config.getShouldPenalize();
        miroStatVersion = config.getMiroStatType();
        num_predict = config.predictNumber();
        
        
    }
    
    public void setNumPredict(int value){
        this.num_predict = value;
    }
    
    public int getNumPredict(){
        return num_predict;
    }
    
    public void setTemperature(float value){
        this.temperature = value;
    }
    
    public float getTemperature(){
        return this.temperature;
    }
    
    
    public void setShouldPenalize(boolean value){
        this.penalize = value;
    }
    
    public boolean getShouldPenalize(){
        return this.penalize;
    }
    
    public void setMiroStatVersion(MiroStat version){
        this.miroStatVersion = version;
    }
    
    public MiroStat getMiroStatVersion(){
        return this.miroStatVersion;
    }
    
    public void setStopString(String value){
        this.stopString = value;
       
    }
    
    public String getStopString (){
        return this.stopString;
    }
    
    
    
    
}
