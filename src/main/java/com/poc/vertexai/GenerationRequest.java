package com.poc.vertexai;

public class GenerationRequest {

    private final String prompt;
    private final float temperature;
    private final int maxOutputTokens;

    private GenerationRequest(Builder builder) {
        this.prompt = builder.prompt;
        this.temperature = builder.temperature;
        this.maxOutputTokens = builder.maxOutputTokens;
    }

    public String getPrompt() {
        return prompt;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getMaxOutputTokens() {
        return maxOutputTokens;
    }

    public static Builder builder(String prompt) {
        return new Builder(prompt);
    }

    public static class Builder {
        private final String prompt;
        private float temperature = 1.0f;
        private int maxOutputTokens = 1024;

        private Builder(String prompt) {
            this.prompt = prompt;
        }

        public Builder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder maxOutputTokens(int maxOutputTokens) {
            this.maxOutputTokens = maxOutputTokens;
            return this;
        }

        public GenerationRequest build() {
            return new GenerationRequest(this);
        }
    }
}
