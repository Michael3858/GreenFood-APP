package com.example.greenfood;

/** Local deployment settings shared by the original Activity classes. */
public final class ApiConfig {
    private ApiConfig() {
    }

    public static String url(String endpoint) {
        String baseUrl = BuildConfig.API_BASE_URL;
        return (baseUrl.endsWith("/") ? baseUrl : baseUrl + "/") + endpoint;
    }

    public static String placeholderImage() {
        return "android.resource://" + BuildConfig.APPLICATION_ID + "/drawable/api_placeholder";
    }
}
