package com.poc.vertexai;

public interface VertexAI {

    void authenticate(Object config);

    String generate(GenerationRequest request);
}
