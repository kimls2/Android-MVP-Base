package com.ykim.android_mvp_base.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ykim.android_mvp_base.BuildConfig;
import com.ykim.android_mvp_base.data.model.GalleryResponse;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ykim on 2017. 4. 11..
 */

public interface ImgurService {

  String ENDPOINT = "https://api.imgur.com";

  @GET("/3/gallery/hot/viral/{window}/{page}.json") Observable<GalleryResponse> getGallery(
      @Path("page") int page);

  class Factory {
    public static ImgurService makeImugurService() {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
          : HttpLoggingInterceptor.Level.NONE);

      OkHttpClient okHttpClient = new OkHttpClient.Builder() //
          .addInterceptor(logging)  //
          .addInterceptor(chain -> {
            Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID fb89f7a636907c2")
                .build();
            return chain.proceed(request);
          }) //
          .build();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

      Retrofit retrofit = new Retrofit.Builder() //
          .baseUrl(ImgurService.ENDPOINT) //
          .client(okHttpClient) //
          .addConverterFactory(GsonConverterFactory.create(gson)) //
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
          .build();
      return retrofit.create(ImgurService.class);
    }
  }
}
