package Servicios.Hogares;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class TokenInterceptor implements Interceptor {

  @Override
  public Response intercept(Interceptor.Chain chain) throws IOException {
    Request newRequest=chain.request().newBuilder()
        .header("Authorization", "Bearer " + "ITAFhCsnV4JYiOkX5DJgCvQTLT2zFj8ShKvgD6lHsxlI6bHjv38OaB7HUksn")
        .build();

    return chain.proceed(newRequest);
  }

}