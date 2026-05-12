package com.poc.vertexai;

import com.poc.vertexai.implementations.GoogleGenAIConfig;
import com.poc.vertexai.implementations.GoogleGenAIVertexAI;
import java.io.IOException;

public class Main {

    private static VertexAI vertexAI;
    private static final String projectId = "";
    private static final String location = "";
    private static final String model = "";
    private static final String serviceAccountFile = "";


    public static void main(String[] args) throws IOException {

        googleGenAIRunner();

        GenerationRequest request = GenerationRequest.builder(
                        "Explain the difference between supervised and unsupervised learning in 3 sentences."
                )
                .temperature(0.7f)
                .maxOutputTokens(512)
                .build();
        String response = vertexAI.generate(request);
        System.out.println("Response:\n" + response);
    }

    // Example using Google GenAI Library
    private static void googleGenAIRunner() throws IOException {

        GoogleGenAIConfig config = GoogleGenAIConfig.builder()
                .projectId(projectId)
                .location(location)
                .model(model)
                .serviceAccountKeyFile(serviceAccountFile)
                .build();

        vertexAI = new GoogleGenAIVertexAI();
        vertexAI.authenticate(config);
    }

}
