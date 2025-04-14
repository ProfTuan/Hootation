/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uthouston.sbmi.hootation.llmenrichment;

import edu.uthouston.sbmi.hootation.llmenrichment.util.LLMConfiguration;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author mac
 */
public class LLMManagement {
    
    static private LLMManagement INSTANCE = null;
    
    private Map<String, String> url_models = null;
    
    private String userDirectory = "";
    
    private LLMManagement(){
        
        url_models = new HashMap<String,String>();
        
    }
    
    
    public void setUserDirectory(String selectedDirectory){
        
        userDirectory = selectedDirectory;
        
        
    }

    static public LLMManagement getInstance(){
        
        if(INSTANCE == null){
            INSTANCE = new LLMManagement();
        }
        
        return INSTANCE;
        
    }
    
    public void addURLForModels(String name, String url){
        
        url_models.put(name, url);
        
    }
    
    
    public String getSelectedURLModel(String name){
        
        String selected = "";
        
        selected = url_models.get(name);
        
        return selected;
    }
    
    public void intializeURLModels(){
        
        url_models.putAll(LLMConfiguration.getInstance().collectLLMList());
        
    }
    
    public void downloadFile(String fileURL) {

        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS) // Automatically follow redirects
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fileURL))
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();

            if (statusCode == 200) {

                long contentLength = Long.parseLong(response.headers().firstValue("Content-Length").orElse("0"));
                try (InputStream inputStream = response.body(); FileOutputStream outputStream = new FileOutputStream(this.userDirectory)) {

                    byte[] buffer = new byte[4096];
                    long totalBytesRead = 0;
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;

                        // Calculate and display progress
                        int progress = (int) (totalBytesRead * 100 / contentLength);
                        System.out.print("\r" + "Downloaded " + progress + "% [" + progressBar(progress) + "]");
                    }
                    System.out.println("\nDownload complete.");

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(LLMManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LLMManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    private void openDefaultBrowser(URI uri)  {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            // windows
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                
            }
        } else {
            // linux / mac
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + uri.toString());
            } catch (IOException e) {
                
            }
        }
    }
    
    

    //JTextArea
    public void downloadFile(String fileURL, String saveDir, JTextArea panelOutput){
        
        //URI uri = URI.create(fileURL);
        
        //openDefaultBrowser(uri);
        
        /*
        File llm_download = File.createTempFile(saveDir + "llm", ".tmp");
        
        Connection conn = Jsoup.connect(fileURL).timeout(300000).header("Cache-Control", "max-age=0")
        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")
        .referrer("http://www.google.com").ignoreContentType(true);
        
        Connection.Response response = conn.execute();
        BufferedInputStream bodyStream = response.bodyStream();
        
        panelOutput.append("\nSaving the file to " + llm_download.getAbsolutePath());
        panelOutput.append("\nDownloading....");
        java.nio.file.Files.copy(bodyStream, llm_download.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        panelOutput.append("\nSaving the file to " + llm_download.getAbsolutePath());
        */
        //Files.copy(bodyStream, llm_download.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS) // Automatically follow redirects
                    .build();
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fileURL))
                    .build();
            
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                long contentLength = Long.parseLong(response.headers().firstValue("Content-Length").orElse("0"));
                try (InputStream inputStream = response.body();
                        FileOutputStream outputStream = new FileOutputStream(saveDir)) {
                    
                    byte[] buffer = new byte[4096];
                    long totalBytesRead = 0;
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        
                        // Calculate and display progress
                        int progress = (int) (totalBytesRead * 100 / contentLength);
                        
                        panelOutput.setText("\r" + "Downloaded " + progress + "% [" + progressBar(progress) + "]");
                    }
                    
                    panelOutput.setText("\nDownload complete.");
                }
            } else {
                
                panelOutput.setText("Failed to download file. HTTP status code: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(LLMManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LLMManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void downloadFile(String fileURL, String saveDir) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Automatically follow redirects
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fileURL))
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        int statusCode = response.statusCode();
        if (statusCode == 200) {
            long contentLength = Long.parseLong(response.headers().firstValue("Content-Length").orElse("0"));
            try (InputStream inputStream = response.body();
                 FileOutputStream outputStream = new FileOutputStream(saveDir)) {

                byte[] buffer = new byte[4096];
                long totalBytesRead = 0;
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;

                    // Calculate and display progress
                    int progress = (int) (totalBytesRead * 100 / contentLength);
                    System.out.print("\r" + "Downloaded " + progress + "% [" + progressBar(progress) + "]");
                }
                System.out.println("\nDownload complete.");
            }
        } else {
            System.out.println("Failed to download file. HTTP status code: " + statusCode);
        }
    }

    private String progressBar(int progress) {
        int totalBars = 50; // The length of the progress bar
        int bars = (progress * totalBars) / 100;
        return "=".repeat(bars) + " ".repeat(totalBars - bars);
    }
    
    /*
    public static void main(String[] args) {
        String fileURL = "https://huggingface.co/TheBloke/CodeLlama-7B-GGUF/resolve/main/codellama-7b.Q2_K.gguf";
        String saveDir = "D:/hugging_scope/modelscope/codellama-7b.Q2_K.gguf";
        try {
            downloadFile(fileURL, saveDir);
            System.out.println(saveDir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    */
    
    public static void main(String[] args) {
        
        LLMManagement llmmanagement = new LLMManagement();
        
        // List of URLs to present to the user
        String[] urls = {
            "https://huggingface.co/TheBloke/CodeLlama-7B-GGUF/resolve/main/codellama-7b.Q2_K.gguf",
            "https://huggingface.co/TheBloke/CodeLlama-7B-GGUF/resolve/main/codellama-7b.Q4_K.gguf",
            "https://huggingface.co/TheBloke/CodeLlama-7B-GGUF/resolve/main/codellama-7b.Q5_K.gguf"
        };

        // Display the list of options to the user
        System.out.println("Please choose a model to download:");
        for (int i = 0; i < urls.length; i++) {
            System.out.println((i + 1) + ": " + urls[i]);
        }

        // Read user input for URL choice
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number corresponding to the model you want to download: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate input
        if (choice < 1 || choice > urls.length) {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        // Set fileURL based on user choice
        String fileURL = urls[choice - 1];

        // Extract the file name from the URL
        String fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1);

        // Prompt the user to enter the save directory
        System.out.print("Enter the directory where you want to save the file: ");
        String saveDir = scanner.nextLine();

        // Combine save directory with file name
        saveDir = saveDir + "/" + fileName;

        // Download the file
        try {
            llmmanagement.downloadFile(fileURL, saveDir);
            System.out.println("Model downloading from: " + fileURL);
            System.out.println("Model downloaded to: " + saveDir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
    
}
