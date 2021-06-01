package com.silva021.githubrepository.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit = sBuilder.build();

    public static <T> T createService(Class<T> serviceClass) {
        return sRetrofit.create(serviceClass);
    }


}
