package com.mirkowu.mvm.network;

import com.mirkowu.lib_network.load.AbsUploadClient;

public class UploadFileClient extends AbsUploadClient {
    @Override
    public String getBaseUrl() {
        return "http://192.168.182.1:8080/";
    }

    private static class Singleton {
        private static final UploadFileClient INSTANCE = new UploadFileClient();
    }

    public static UploadFileClient getInstance() {
        return Singleton.INSTANCE;
    }

    private UploadFileClient() {
    }

    private static <T> T getAPIService(Class<T> service) {
        return getInstance().getService(service);
    }

    public static FileApi getUploadFileApi() {
        return getAPIService(FileApi.class);
    }

}
