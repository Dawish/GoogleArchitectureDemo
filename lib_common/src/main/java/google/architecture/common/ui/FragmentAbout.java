package google.architecture.common.ui;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import google.architecture.common.R;
import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseFragment;
import google.architecture.common.databinding.FragmentAboutBinding;

/**
 * 关于页面
 */
@Route(path = ARouterPath.AboutFgt)
public class FragmentAbout extends BaseFragment {

    private FragmentAboutBinding fragmentAboutBinding;

    private UserData userData1,userData2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAboutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);

        List<UserData> userList = new ArrayList<>();

        userData1 = new UserData();
        userData1.setUserName("dawish");
        userData1.setUserId("1212");

        userData2 = new UserData();
        userData2.setUserName("dawish_233");
        userData2.setUserId("2331");

        userList.add(userData1);
        userList.add(userData2);

        fragmentAboutBinding.setUserList(userList);
        fragmentAboutBinding.setFragmentAbout(this);

        /**执行executePendingBindings方法开始数据绑定*/
        fragmentAboutBinding.executePendingBindings();

        LogUtils.d("fragmentAboutBinding--->");

        fragmentAboutBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeName();
            }
        });

        return fragmentAboutBinding.getRoot();
    }

    public void changeName(){
        userData1.setUserName("Dawish_大D");
        userData2.setUserName("Dawish_大D_233");
    }

}
