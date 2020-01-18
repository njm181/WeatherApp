package com.njm.weatherapp.model;

import com.njm.weatherapp.Utils.Constants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        HttpUrl originalUrl = originalRequest.url();

        HttpUrl newUrl = originalUrl.newBuilder()
                .addQueryParameter("appid", Constants.API_KEY)
                .build();

        Request finalRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build();

        return chain.proceed(finalRequest);
    }
}
