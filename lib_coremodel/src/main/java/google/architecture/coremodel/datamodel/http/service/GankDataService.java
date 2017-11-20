package google.architecture.coremodel.datamodel.http.service;

import google.architecture.coremodel.datamodel.http.Model;
import google.architecture.coremodel.datamodel.http.entities.NewsData;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by dxx on 2017/11/8.
 */

public interface GankDataService {

    @GET  ("api/data/福利/{size}/{index}")
    Observable<GirlsData> getFuliData(@Path("size") String size, @Path("index") String index);

    @GET("api/data/Android/{size}/{index}")
    Observable<NewsData> getAndroidData(@Path("size") String size, @Path("index") String index);

}
