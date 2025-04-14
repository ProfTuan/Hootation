package edu.uthouston.sbmi.hootation.llmenrichment;


import java.io.IOException;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.LlamaOutput;
import de.kherud.llama.ModelParameters;
import de.kherud.llama.args.MiroStat;
import edu.uthouston.sbmi.hootation.llmenrichment.model.LLMParameters;
import edu.uthouston.sbmi.hootation.llmenrichment.model.NLAxiomData;
import edu.uthouston.sbmi.hootation.llmenrichment.util.LLMConfiguration;
import edu.uthouston.sbmi.hootation.llmenrichment.util.Reporter;
import edu.uthouston.sbmi.hootation.models.OutputRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

//@SuppressWarnings("InfiniteLoopStatement")
public class LLMEnrichment 
{
    
   private LLMParameters llm_parameters = null;
    
    private ModelParameters modelParams = null;
    private InferenceParameters inferParams = null;
    
    private Reporter llmReporter = null;
    
    private String model_path ="";
    private int thread_number;
    private int gpu_layers;
    
    public LLMEnrichment (){
        
        llm_parameters = LLMParameters.getInstance();
        
    }    
    
    /*
    public List<String[]> readcsv(String filepath) {        
        List<String[]> records = null;
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {
            records = reader.readAll();            
            
            for (String[] record : records) {                
                System.out.println("Record: " + record.length);
                for (String field : record) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }
            
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return records;
    }
    */
    
    public void setModelParameters(String modelPath, int thread_number, int gpu_layers){
        
        this.model_path = modelPath;
        this.thread_number = thread_number;
        this.gpu_layers = gpu_layers;
    }
    
    public void setInferenceParamters(float temp, boolean penalize, MiroStat ms, String stop_string, int num_predict){
        
    }
    
    public void downloadAndSetModelPath(String fileURL, String saveDir)  {
       try {
           // Use LLMManagement to download the model
           LLMManagement.getInstance().downloadFile(fileURL, saveDir);
       } catch (IOException ex) {
           Logger.getLogger(LLMEnrichment.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InterruptedException ex) {
           Logger.getLogger(LLMEnrichment.class.getName()).log(Level.SEVERE, null, ex);
       }
        // Set the downloaded model's path as the model_path
        this.model_path = saveDir;
    }
    
    // TODO: we need to move this to the Reporter
    /*
    public void writeCsv(String filePath, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
  
    public void initModelParameters(){
        
        modelParams = new ModelParameters();
        LLMConfiguration llmconfig = LLMConfiguration.getInstance();
        
        modelParams.setModel(llmconfig.getModelFilePath());
        modelParams.setThreads(llmconfig.getNumThreads());
        modelParams.setGpuLayers(llmconfig.getLayers());
        
        /*modelParams.setModelFilePath(llmconfig.getModelFilePath());
        modelParams.setNThreads(llmconfig.getNumThreads());
        modelParams.setNGpuLayers(llmconfig.getLayers());*/
        
    }
    
    public String translateAxiom(String nl_string, String axiom_type){
        
        StringBuilder results = new StringBuilder();
        
        final String template_prompt = "You are a helpful assistant\n. User: Please translate the ontology axiom using natural langauge. The axiom type is: [axiom_type]. The axiom you need to translate is:  [axiom] . Your translation for this axiom is (Just state your translation in one sentence. Do not add any other statements):";
        
        modelParams = new ModelParameters();
        LLMConfiguration llmconfig = LLMConfiguration.getInstance();
        
        modelParams.setModel(llmconfig.getModelFilePath());
        modelParams.setThreads(llmconfig.getNumThreads());
        modelParams.setGpuLayers(llmconfig.getLayers());
        
        /*modelParams.setModelFilePath(llmconfig.getModelFilePath());
        modelParams.setNThreads(llmconfig.getNumThreads());
        modelParams.setNGpuLayers(llmconfig.getLayers());*/
        
        LlamaModel model = new LlamaModel(modelParams);
        
        String prompt_temp = template_prompt
                        .replaceAll("\\[axiom_type\\]", axiom_type)
                        .replaceAll("\\[axiom\\]", nl_string);
        
        inferParams = new InferenceParameters(prompt_temp)
                        .setTemperature(llmconfig.getTemperature())
                        .setPenalizeNl(llmconfig.getShouldPenalize())
                        .setMiroStat(llmconfig.getMiroStatType())
                        .setStopStrings("User:")
                        .setNPredict(llmconfig.predictNumber());
        
        for(LlamaOutput output: model.generate(inferParams)){
            System.out.println(output);
            results.append(output);
        }
        
        return results.toString();
        
    }
            
    public void translateAxioms(ArrayList<OutputRecord> records){
        
        LLMConfiguration llmconfig = LLMConfiguration.getInstance();
        
        final String template_prompt = "You are a helpful assistant\n. User: Please translate the ontology axiom using natural langauge. The axiom type is: [axiom_type]. The axiom you need to translate is:  [axiom] . Your translation for this axiom is (Just state your translation in one sentence. Do not add any other statements):";
        
        modelParams = new ModelParameters();
        
        modelParams.setModel(llm_parameters.getFileModelPath());
        modelParams.setThreads(llm_parameters.getNThreads());
        modelParams.setGpuLayers(llm_parameters.getNGpuLayers());
        
        /*modelParams.setModelFilePath(llm_parameters.getFileModelPath());
        modelParams.setNThreads(llm_parameters.getNThreads());
        modelParams.setNGpuLayers(llm_parameters.getNGpuLayers());*/
        
        //ArrayList<String> result_data = new ArrayList();
        
        try(LlamaModel model = new LlamaModel(modelParams)){
            
            for(OutputRecord record: records){
                StringBuilder results = new StringBuilder();
                String prompt_temp = template_prompt
                        .replaceAll("\\[axiom_type\\]", record.getAxiom_type().toString())
                        .replaceAll("\\[axiom\\]", record.getNatural_language());
                
                
                inferParams = new InferenceParameters(prompt_temp)
                        .setTemperature(llm_parameters.getTemperature())
                        .setPenalizeNl(llm_parameters.getShouldPenalize())
                        .setMiroStat(llm_parameters.getMiroStatVersion())
                        .setStopStrings("User:")
                        .setNPredict(llm_parameters.getNPredict());
                
                
                for(LlamaOutput output: model.generate(inferParams)){
                    System.out.println(output);
                    results.append(output);
                    
                }
               record.setLLMNaturalLanguageTranslation(results.toString());
            }
            
        }
        
        
        
    }
    
    //PREVIOUS VERSION
    @Deprecated
    public void inference(String sourcepath, String targetpath, String modelpath) throws IOException {
        llmReporter = Reporter.getInstance();
        List<String[]> records = llmReporter.readcsv(sourcepath);
        
        
        modelParams = new ModelParameters()
            .setModel(modelpath)
            //.setModelFilePath(modelpath)
            //.setModelUrl("https://huggingface.co/TheBloke/CodeLlama-7B-GGUF/resolve/main/codellama-7b.Q2_K.gguf")
            //.setHuggingFaceRepository("TheBloke/CapybaraHermes-2.5-Mistral-7B-GGUF")
            //.setHuggingFaceFile("capybarahermes-2.5-mistral-7b.Q2_K.gguf")
            .setThreads(16)
            .setGpuLayers(43);
                //.setNThreads(16)
            //.setNGpuLayers(43);
      
        List<String[]> outdata = new ArrayList<>();
        
        try (LlamaModel model = new LlamaModel(modelParams)) {
            System.out.println("inference...");    
            
            String system = "\nYou are a helpful assistant. ";
            String question = "Please translate the ontology axiom using natural language. ";        
            for(String[] input: records) { 
                if (input[0].trim().equals("Axiom Type") || input[0].trim().length()<2){
                    outdata.add(input);
                    continue;
                }
                
                String axiom_type = "The axiom type is: " + input[0] + ". ";
                String axiom = "The axiom you need to translate is: " + input[1] + ". ";
                String prompt = system + "\nUser: " + question + axiom_type + axiom + 
                                "Your translation for this axiom is (Just state your translation in one sentence. Do not add any other statements):";                                                
                                  
                System.out.println("prompt:  " + prompt);
                
                inferParams = new InferenceParameters(prompt)
                    .setTemperature(0.7f)
                    .setPenalizeNl(true)
                    .setMiroStat(MiroStat.V2)
                    .setStopStrings("User:")
                    .setNPredict(30);
                
                String data = "";
                for (LlamaOutput output : model.generate(inferParams)) {                    
                    data += output;
                }
                System.out.println("###trans:   "+data);
                
                String[] temp = new String[input.length+1];                
                System.arraycopy(input, 0, temp, 0, input.length);
                int len = data.indexOf(".", 0);
                if (len != -1)                    
                    data = data.substring(0, len);                
                data = data.replace("\n", " ");
                temp[temp.length-1] = data;                
                outdata.add(temp);
                llmReporter.writeCsv(targetpath, outdata);    
            } 
            llmReporter.writeCsv(targetpath, outdata);
        }
    }
    
    public static void main(String[] args){
        System.setProperty("de.kherud.llama.lib.path", "D:/AAAAA_pythonProject/amith/java-llama.cpp/src/main/resources/de/kherud/llama/Windows/x86_64");
        System.out.println(System.getProperty("de.kherud.llama.lib.path"));
        // System.exit(0);
        
        /*
        System.out.println("Arguments length: " + args.length);
        if (args.length < 1) {
            System.out.println("Usage: java LLMEnrichment <modelSaveDir>");
            //return;
        }
        String saveDir = args[0];
        */

        LLMEnrichment infer = new LLMEnrichment();
        String sourcepath = "D:/netbean_project/LLMEnrichment_local/data/People Axioms 11_18.csv";
        String respath = "D:/netbean_project/LLMEnrichment_local/result/t.csv";
        //infer.writeCsv(respath, records);
        
        try{
            //TODO: We need a class that can download and import a selected model
            String modelpath = "D:/hugging_scope/modelscope/codellama-7b.Q2_K.gguf";
            infer.inference(sourcepath, respath, modelpath);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
