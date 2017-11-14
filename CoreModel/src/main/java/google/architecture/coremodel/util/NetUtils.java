package google.architecture.coremodel.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {

    public static final int DISCONNECTED = 0;
    public static final int WIFI_CONNECTED = 1;
    public static final int ETHERNET_CONNECTED = 2;

    public static int getNetConnStatus(Context context) {
        if (context == null) {
            return DISCONNECTED;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        int status = DISCONNECTED;

        if (wifiNetInfo != null && wifiNetInfo.isAvailable() && wifiNetInfo.isConnected()) {
            status = WIFI_CONNECTED;
        } else {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                status = ETHERNET_CONNECTED;
            }
        }

        return status;
    }

    /**
     * 当前网络是否已连接
     */
    public static boolean isNetConnected(Context context) {
        if (context == null) {
            return false;
        }

        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo[] infos = connectivity.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo info : infos) {
                        if (info != null && info.isConnected()) {
                            // 判断当前网络是否已经连接
                            if (info.getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 当前网络是否已连接
     */
    public static LiveData<Boolean> netConnected(Context context) {
        MutableLiveData<Boolean> isNetConnect = new MutableLiveData<>();
        if (context == null) {
            isNetConnect.setValue(false);
            return isNetConnect;
        }

        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo[] infos = connectivity.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo info : infos) {
                        if (info != null && info.isConnected()) {
                            // 判断当前网络是否已经连接
                            if (info.getState() == NetworkInfo.State.CONNECTED) {
                                isNetConnect.setValue(true);
                                return isNetConnect;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isNetConnect.setValue(false);
        return isNetConnect;
    }

}
