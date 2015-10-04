package com.gerardogtn.hha.data.remote;

import android.os.Bundle;

import retrofit.RestAdapter;

/**
 * Created by root on 4/10/15.
 */
public class APIClent {

    public static APIService API_SERVICE;

    public static APIService newInstance() {

        if (API_SERVICE == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(APIConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            API_SERVICE = restAdapter.create(APIService.class);
        }
        return  API_SERVICE;
    }
}
