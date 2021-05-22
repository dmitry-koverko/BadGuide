package com.kawka.badguide.data.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Domain.getBaseBomain())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
