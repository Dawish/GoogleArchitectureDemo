package debug;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import google.architecture.common.base.BaseApplication;

/**
 * Created by dxx on 2017/11/15.
 * 组件化编译的时候才生效
 */

public class GirlsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
