package com.example.mareunion;

public class DI {
    private static final ApiService service = new ApiService() {
    };

    public static ApiService getApiService() {
        return service;
    }
}
