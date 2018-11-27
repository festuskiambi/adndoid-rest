package com.example.festus.resttuitorial.error;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed((chain.request()));

        if(!response.isSuccessful()){
            throw  new GithubException(
                    response.code(),
                    response.message()
            );
        }

        return response;
    }
}
