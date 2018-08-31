package com.agit.lutluthfi.mvpboilerplate.network;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    private NetworkModule() {
        // This class is not publicly instantiate
    }

    public static Retrofit create(String baseUrl) {
        OkHttpClient client = createHttpClient();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient createHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetworkConstant.CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(NetworkConstant.CONNECTION_READ_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(true);
        builder.connectionPool(new ConnectionPool(
                NetworkConstant.CONNECTION_MAX_IDLE,
                NetworkConstant.CONNECTION_ALIVE_DURATION,
                TimeUnit.MILLISECONDS));
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
