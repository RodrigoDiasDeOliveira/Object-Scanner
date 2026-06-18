package com.example.objectscanner;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ObjectCountResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("counts")
    private Map<String, Integer> counts;

    @SerializedName("source")
    private String source;

    public boolean isSuccess() { return success; }
    public Map<String, Integer> getCounts() { return counts; }
    public String getSource() { return source; }
}