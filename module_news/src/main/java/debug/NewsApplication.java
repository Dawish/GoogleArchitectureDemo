package debug;

import com.facebook.drawee.backends.pipeline.Fresco;

import google.architecture.common.base.BaseApplication;

/**
 * Created by dxx on 2017/11/16.
 */

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
