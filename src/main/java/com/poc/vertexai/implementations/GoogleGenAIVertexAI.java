package com.poc.vertexai.implementations;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.poc.vertexai.GenerationRequest;
import com.poc.vertexai.VertexAI;

public class GoogleGenAIVertexAI implements VertexAI {

    private Client client;
    private String model;

    @Override
    public void authenticate(Object config) {
        if (!(config instanceof GoogleGenAIConfig vertexAIConfig)) {
            throw new IllegalArgumentException("Expected GoogleGenAIConfig");
        }

        this.model = vertexAIConfig.getModel();

        this.client = Client.builder()
                .vertexAI(true)
                .project(vertexAIConfig.getProjectId())
                .location(vertexAIConfig.getLocation())
                .credentials(vertexAIConfig.getCredentials())
                .build();
    }

    @Override
    public String generate(GenerationRequest request) {
        if (client == null) {
            throw new IllegalStateException("Call authenticate() before generate()");
        }

        GenerateContentConfig genConfig = GenerateContentConfig.builder()
                .temperature(request.getTemperature())
                .maxOutputTokens(request.getMaxOutputTokens())
                .build();

        GenerateContentResponse response = client.models.generateContent(
                model,
                request.getPrompt(),
                genConfig
        );

        return response.text();
    }
}
