package google.architecture.common.ui;

import android.databinding.ObservableField;

/**
 * Created by danxx on 2017/12/3.
 * 简写版支持双向绑定的数据
 */

public class UserDataOF {

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userId = new ObservableField<>();

}
