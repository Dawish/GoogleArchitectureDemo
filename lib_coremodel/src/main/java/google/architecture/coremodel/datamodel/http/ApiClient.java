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

        GankDataService gankDataService = initService(ApiConstants.fuliHost, GankDataService.class);

        return gankDataService;
    }

    /**
     * 动态url获取数据
     * @param url
     * @return
     */
    public static DynamicApiService getDynamicDataService(String url){

        DynamicApiService dynamicApiService = ApiClient.initService(url, DynamicApiService.class);

        return dynamicApiService;
    }
    /**
     * 获得想要的 retrofit service
     * @param baseUrl  数据请求url
     * @param clazz    想要的 retrofit service 接口，Retrofit会代理生成对应的实体类
     * @param <T>
     * @return
     */
    public static <T> T initService(String baseUrl, Class<T> clazz) {

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
