# Vertex AI POC

A standalone Java application to test different libraries and approaches for generating text content using Google Vertex AI.

## Project Structure

```
src/main/java/com/poc/vertexai/
├── VertexAI.java                        # Interface with authenticate() and generate() methods
├── GenerationRequest.java               # Request object (prompt, temperature, maxOutputTokens)
├── Main.java                            # Entry point — configures and runs implementations
└── implementations/
    ├── GoogleGenAIConfig.java           # Configuration for the google-genai implementation
    └── GoogleGenAIVertexAI.java         # Implementation using com.google.genai (google-genai SDK)
```

## Architecture

The project defines a `VertexAI` interface with two methods:

- `authenticate(Object config)` — receives an implementation-specific configuration object containing credentials and project settings.
- `generate(GenerationRequest request)` — receives generation parameters (prompt, temperature, max output tokens) and returns the generated text.

Each implementation lives in the `implementations` package with its own config class and `VertexAI` implementation.

## Implementations

### google-genai (`com.google.genai:google-genai`)

- **Config class:** `GoogleGenAIConfig`
- **Implementation:** `GoogleGenAIVertexAI`
- Uses the Google GenAI SDK with `Client.builder().vertexAI(true)` to route requests through Vertex AI.
- Credentials are passed explicitly via `GoogleCredentials`.

## Prerequisites

- Java 21
- Maven
- A GCP project with the Vertex AI API enabled
- A service account key file (JSON) with the `Vertex AI User` role, or Application Default Credentials configured

## Configuration

In `Main.java`, set the following fields:

| Field              | Description                                      |
|--------------------|--------------------------------------------------|
| `projectId`        | Your GCP project ID                              |
| `location`         | Vertex AI region (e.g. `us-central1`)            |
| `model`            | Model ID (e.g. `gemini-2.0-flash-001`)           |
| `serviceAccountFile` | Path to the service account JSON key file      |

## Running

```bash
mvn compile exec:java -Dexec.mainClass=com.poc.vertexai.Main
```

## Adding a New Implementation

1. Create a config class in `implementations/` (e.g. `MyLibConfig.java`)
2. Create an implementation class that implements `VertexAI` (e.g. `MyLibVertexAI.java`)
3. Add a runner method in `Main.java` following the same pattern as `googleGenAIRunner()`
4. Add the required dependency to `pom.xml`
