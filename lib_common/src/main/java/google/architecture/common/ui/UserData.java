package google.architecture.common.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import google.architecture.common.BR;

/**
 * Created by Danxx on 2017/12/2.
 * 原始版支持双向绑定的数据
 */

public class UserData extends BaseObservable {

    private String userName;
    private String userId;

    /**
     * get方法上面添加@Bindable注解为了实现自动更新
     * @return
     */
    @Bindable
    public String getUserName() {
        return userName;
    }

    /**
     * set方法中添加notifyPropertyChanged();这一句代码，这里是提醒更新数据
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }
}
