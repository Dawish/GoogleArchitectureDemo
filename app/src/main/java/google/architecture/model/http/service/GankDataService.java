package google.architecture.model.http.service;

import google.architecture.model.http.entities.AndroidData;
import google.architecture.model.http.entities.FuliData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dxx on 2017/11/8.
 */

public interface GankDataService {

    @GET("api/data/福利/{size}/{index}")
    Observable<FuliData> getFuliData(@Path("size") String size, @Path("index") String index);

    @GET("api/data/Android/{size}/{index}")
    Observable<AndroidData> getAndroidData(@Path("size") String size, @Path("index") String index);

}
