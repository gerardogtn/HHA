package com.gerardogtn.hha.data.remote;

import com.gerardogtn.hha.data.model.WakeUp;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by root on 4/10/15.
 */
public interface APIService {

    @POST(APIConstants.ADD_DATE_POST)
    void addDatePost(@Body WakeUp wakeUp, Callback<WakeUp> responseCallback);

}
