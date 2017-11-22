package google.architecture.coremodel.datamodel.http.repository;

import android.arch.lifecycle.MutableLiveData;

import google.architecture.coremodel.datamodel.http.ApiClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dxx on 2017/11/20.
 * 动态数据获取
 */

public class DynamicDataRepository {

    public static <T> T getDynamicData(String url, Class<T> clazz) {

        MutableLiveData<T> applyData = new MutableLiveData<>();

        ApiClient.getDynamicDataService(url).getDynamicData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(T value) {
                applyData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return applyData.getValue();
    }

    /**
     * 获取动态url的Observable
     * @param url
     * @return
     */
    public static Observable getDynamicDataObservable(String url) {

        return ApiClient.getDynamicDataService(url).getDynamicData(url);
    }
}