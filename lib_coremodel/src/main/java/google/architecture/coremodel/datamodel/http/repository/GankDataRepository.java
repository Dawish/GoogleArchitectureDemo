package google.architecture.coremodel.datamodel.http.repository;

import google.architecture.coremodel.datamodel.http.ApiClient;
import google.architecture.coremodel.datamodel.http.entities.NewsData;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import io.reactivex.Observable;

/**
 * Created by dxx on 2017/11/8.
 */

public class GankDataRepository {

    public static Observable<GirlsData>  getFuliDataRepository(String size, String index){

        Observable<GirlsData> observableForGetFuliDataFromNetWork = ApiClient.getGankDataService().getFuliData(size,index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetFuliDataFromNetWork;
    }

    public static Observable<NewsData> getNewsDataRepository(String size, String index){

        Observable<NewsData> observableForGetAndroidDataFromNetWork = ApiClient.getGankDataService().getAndroidData(size,index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetAndroidDataFromNetWork;
    }

}
