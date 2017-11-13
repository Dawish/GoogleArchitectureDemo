package google.architecture;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dxx on 2017/11/13.
 */

public class App  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
