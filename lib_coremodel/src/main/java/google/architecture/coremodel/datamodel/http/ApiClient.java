package google.architecture.coremodel.datamodel.http;


import google.architecture.coremodel.BuildConfig;
import google.architecture.coremodel.datamodel.http.service.DynamicApiService;
import google.architecture.coremodel.datamodel.http.service.GankDataService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dxx on 2017/11/8.
 */

public class ApiClient{

    /**
     * 获取指定数据类型
     * @return
     */
    public static GankDataService getGankDataService(){

        GankDataService gankDataService = initService(ApiConstants.GankHost, GankDataService.class);

        return gankDataService;
    }

    /**
     * 动态url获取数据
     * @return
     */
    public static DynamicApiService getDynamicDataService(){

        DynamicApiService dynamicApiService = ApiClient.initService("", DynamicApiService.class);

        return dynamicApiService;
    }
    /**
     * 获得想要的 retrofit service
     * @param baseUrl  数据请求url
     * @param clazz    想要的 retrofit service 接口，Retrofit会代理生成对应的实体类
     * @param <T>      api service
     * @return
     */
    private static <T> T initService(String baseUrl, Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }

    /**单例retrofit*/
    private static Retrofit retrofitInstance;
    private static Retrofit getRetrofitInstance(){
        if (retrofitInstance == null) {
            synchronized (ApiClient.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(ApiConstants.GankHost)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(getOkHttpClientInstance())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    /**单例OkHttpClient*/
    private static OkHttpClient okHttpClientInstance;
    private static OkHttpClient getOkHttpClientInstance(){
        if (okHttpClientInstance == null) {
            synchronized (ApiClient.class) {
                if (okHttpClientInstance == null) {
                    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(httpLoggingInterceptor);
//                      builder.addNetworkInterceptor(new StethoInterceptor());
//                      BuildConfig.STETHO.addNetworkInterceptor(builder);
                    }
                    okHttpClientInstance = builder.build();
                }
            }
        }
        return okHttpClientInstance;
    }

}
