package google.architecture.common.ui;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.ActivityCompat;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by danxx on 2018/4/1.
 * 利用LiveData单例来实时更新状态和监听
 * 定位介绍文章：https://blog.csdn.net/leibing891213/article/details/51423042
 */

public class LocationLiveData extends LiveData<Location> {
    private static LocationLiveData sInstance;
    private LocationManager locationManager;
    private static Context context;

    // GPS定位
    private final static String GPS_LOCATION = LocationManager.GPS_PROVIDER;
    // 网络定位
    private final static String NETWORK_LOCATION = LocationManager.NETWORK_PROVIDER;
    // 解码经纬度最大结果数目
    private final static int MAX_RESULTS = 1;
    // 时间更新间隔，单位：ms
    private final static long MIN_TIME = 1000;
    // 位置刷新距离，单位：m
    private final static float MIN_DISTANCE = (float) 0.01;

    @MainThread
    public static LocationLiveData get(Context ctxt) {
        if (sInstance == null) {
            context = ctxt;
            sInstance = new LocationLiveData(context.getApplicationContext());
        }
        return sInstance;
    }

    private SimpleLocationListener listener = new SimpleLocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
    }

    @Override
    protected void onActive() {
        LogUtils.d("====onActive===111");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LogUtils.d("====onActive===222");
        locationManager.requestLocationUpdates(NETWORK_LOCATION, MIN_TIME, MIN_DISTANCE, listener);
    }

    @Override
    protected void onInactive() {
        LogUtils.d("====onInactive===");
        locationManager.removeUpdates(listener);
    }
}