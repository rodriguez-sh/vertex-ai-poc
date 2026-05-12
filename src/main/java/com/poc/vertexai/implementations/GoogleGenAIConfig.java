package com.poc.vertexai.implementations;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class GoogleGenAIConfig {

    private final String projectId;
    private final String location;
    private final String model;
    private final GoogleCredentials credentials;

    private GoogleGenAIConfig(Builder builder) {
        this.projectId = builder.projectId;
        this.location = builder.location;
        this.model = builder.model;
        this.credentials = builder.credentials;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    public GoogleCredentials getCredentials() {
        return credentials;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;
        private String location = "";
        private String model = "";
        private GoogleCredentials credentials;

        private static final List<String> VERTEX_SCOPES =
                List.of("https://www.googleapis.com/auth/cloud-platform");

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /** Explicit service-account key file (JSON). */
        public Builder serviceAccountKeyFile(String path) throws IOException {
            this.credentials = GoogleCredentials
                    .fromStream(new FileInputStream(path))
                    .createScoped(VERTEX_SCOPES);
            return this;
        }

        /** Explicit GoogleCredentials — use when you already hold a credentials object. */
        public Builder credentials(GoogleCredentials credentials) {
            this.credentials = credentials;
            return this;
        }

        /**
         * Application Default Credentials — resolves via gcloud ADC, Workload Identity,
         * or the GOOGLE_APPLICATION_CREDENTIALS env var.
         */
        public Builder applicationDefaultCredentials() throws IOException {
            this.credentials = GoogleCredentials
                    .getApplicationDefault()
                    .createScoped(VERTEX_SCOPES);
            return this;
        }

        public GoogleGenAIConfig build() {
            if (projectId == null || projectId.isBlank()) {
                throw new IllegalArgumentException("projectId is required");
            }
            if (credentials == null) {
                throw new IllegalArgumentException(
                        "credentials are required — call applicationDefaultCredentials(), " +
                        "serviceAccountKeyFile(), or credentials()");
            }
            return new GoogleGenAIConfig(this);
        }
    }
}
