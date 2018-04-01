package google.architecture.coremodel.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import google.architecture.coremodel.datamodel.http.ApiClient;
import google.architecture.coremodel.datamodel.http.ApiConstants;
import google.architecture.coremodel.datamodel.http.repository.DynamicDataRepository;
import google.architecture.coremodel.datamodel.http.service.DynamicApiService;
import google.architecture.coremodel.util.JsonUtil;
import google.architecture.coremodel.util.SwitchSchedulers;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by dxx on 2017/11/20.
 */

public class BaseViewModel<T> extends AndroidViewModel {

    //生命周期观察的数据
    private MutableLiveData<T>  liveObservableData = new MutableLiveData<>();
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<T> uiObservableData = new ObservableField<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    public BaseViewModel(@NonNull Application application) {
        super(application);
        LogUtils.d("=======BaseViewModel--onCreate=========");
    }

    /**
     *
     * @param fullUrl
     */
    public void loadData( String fullUrl ){
        DynamicDataRepository.getDynamicData(fullUrl, getTClass())
                .compose(SwitchSchedulers.applySchedulers())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(T value) {
                        if(null != value){
                            liveObservableData.setValue(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * LiveData支持了lifecycle生命周期检测
     * @return
     */
    public LiveData<T> getLiveObservableData() {
        return liveObservableData;
    }

    /**
     * 当主动改变数据时重新设置被观察的数据
     * @param product
     */
    public void setUiObservableData(T product) {
        this.uiObservableData.set(product);
    }

    public Class<T> getTClass(){
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
        LogUtils.d("=======BaseViewModel--onCleared=========");
    }
}
