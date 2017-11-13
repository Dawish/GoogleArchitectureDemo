package google.architecture.model.http;

import android.support.compat.BuildConfig;

import google.architecture.model.http.service.GankDataService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dxx on 2017/11/8.
 */

public class ApiClient{

    public static GankDataService getGankDataService(){

        GankDataService gankDataService = initService(ApiConstants.fuliHost, GankDataService.class);

        return gankDataService;
    }

    /**
     * 获得想要的 retrofit service
     * @param baseUrl  数据请求url
     * @param clazz    想要的 retrofit service 接口，Retrofit会代理生成对应的实体类
     * @param <T>
     * @return
     */
    private static <T> T initService(String baseUrl, Class<T> clazz) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
//            builder.addNetworkInterceptor(new StethoInterceptor());
//            BuildConfig.STETHO.addNetworkInterceptor(builder);
        }
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }

}
