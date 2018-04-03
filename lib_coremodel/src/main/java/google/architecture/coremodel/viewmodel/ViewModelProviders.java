package google.architecture.coremodel.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStores;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by danxx on 2018/3/31.
 * Global ViewModel Provider
 * ViewModel的创建不可直接new，需要使用这个{@link ViewModelProviders}才能与Activity或者
 * Fragment的生命周期关联起来！
 */

public class ViewModelProviders {

    /**
     * 通过Activity获取可用的Application
     * 或者检测Activity是否可用
     * @param activity
     * @return
     */
    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    /**
     * 通过Fragment获取Activity
     * 或者检测Fragment是否可用
     * @param fragment
     * @return
     */
    private static Activity checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
        return activity;
    }

    /**
     * 通过Fragment获得ViewModelProvider
     * @param fragment
     * @return
     */
    @NonNull
    @MainThread
    public static ViewModelProvider of(@NonNull Fragment fragment) {

        /**获取默认的单例AndroidViewModelFactory，它内部是通过反射来创建具体的ViewModel*/
        ViewModelProvider.AndroidViewModelFactory factory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(
                        checkApplication(checkActivity(fragment)));
        /***
         *   利用HolderFragment来关联生命周期并使用HolderFragment中的ViewModelStore的HashMap存储ViewModel
         *   AndroidViewModelFactory创建ViewModel
         */
        return new ViewModelProvider(ViewModelStores.of(fragment), factory);
    }

    /**
     * 通过FragmentActivity获得ViewModelProvider
     * @param activity
     * @return
     */
    @NonNull
    @MainThread
    public static ViewModelProvider of(@NonNull FragmentActivity activity) {
        /**获取默认的单例AndroidViewModelFactory，它内部是通过反射来创建具体的ViewModel*/
        ViewModelProvider.AndroidViewModelFactory factory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(
                        checkApplication(activity));
        /***
         *   利用HolderFragment来关联生命周期并使用HolderFragment中的ViewModelStore的HashMap存储ViewModel
         *   AndroidViewModelFactory创建ViewModel
         */
        return new ViewModelProvider(ViewModelStores.of(activity), factory);
    }

    /**
     *
     * @param fragment
     * @param factory 提供了自定义创建ViewModel的方法
     * @return
     */
    @NonNull
    @MainThread
    public static ViewModelProvider of(@NonNull Fragment fragment, @NonNull ViewModelProvider.Factory factory) {
        //检测Fragment
        checkApplication(checkActivity(fragment));
        return new ViewModelProvider(ViewModelStores.of(fragment), factory);
    }

    /**
     *
     * @param activity
     * @param factory 提供了自定义创建ViewModel的方法
     * @return
     */
    @NonNull
    @MainThread
    public static ViewModelProvider of(@NonNull FragmentActivity activity,
                                       @NonNull ViewModelProvider.Factory factory) {
        //检测activity
        checkApplication(activity);
        return new ViewModelProvider(ViewModelStores.of(activity), factory);
    }
}
