package com.example.mareunion.controler;

public class DI {
    private static final DummyApiService service = new DummyApiService() {
    };

    public static DummyApiService getApiService() {
        return service;
    }
}
