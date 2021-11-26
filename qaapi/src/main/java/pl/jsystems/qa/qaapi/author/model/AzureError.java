package pl.jsystems.qa.qaapi.author.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AzureError {
    public String type;
    public String title;
    public int status;
    public String traceId;
    public ErrorType errors;

    public class ErrorType {

        @JsonProperty("$.id")
        public List<String> ids;
    }
}
