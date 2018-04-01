package google.architecture.universal;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseActivity;
import google.architecture.common.service.TestService;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.universal.databinding.ActivityMainBinding;

public class ActivityMain extends BaseActivity {
    ActivityMainBinding binding;

    @Autowired(name = "/service/test")
    TestService testService1;

    TestService testService2;
    TestService testService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入才可以自动初始化Autowired注解声明的变量
        ARouter.getInstance().inject(ActivityMain.this);

        testService1.sayHello("Autowired invoke 233");

        testService2 = ARouter.getInstance().navigation(TestService.class);
        testService2.sayHello("navigation invoke 233");

        testService3 = (TestService) ARouter.getInstance().build("/service/test").navigation();
        testService3.sayHello("build invoke 233");

        setTitle("UniversalApp_ActivityMain");
        Log.i("danxx", "onCreate onCreate");
        binding = DataBindingUtil.setContentView(ActivityMain.this, R.layout.activity_main);
        binding.setItemClick(itemClick);

    }

    private ItemClick itemClick = new ItemClick() {
        @Override
        public void onClick(int id) {
            switch (id){
                case R.id.toGirls:
                    Log.i("danxx", "onClick toGirls");
                    //跳转到GirlsActivity
                    ARouter.getInstance()
                            .build(ARouterPath.GirlsListAty)
                            /**可以针对性跳转跳转动画*/
                            .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                            .navigation(ActivityMain.this);
                    break;
                case R.id.toNews:
                    Log.i("danxx", "onClick toNews");
                    //跳转到NewsActivity
                    ARouter.getInstance()
                            .build(ARouterPath.NewsListAty)
                            .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                            .navigation(ActivityMain.this, 2, new NavigationCallback() {
                                @Override
                                public void onFound(Postcard postcard) {
                                    Log.i("danxx", "ARouter onFound 找到跳转匹配路径");
                                }

                                @Override
                                public void onLost(Postcard postcard) {
                                    Log.i("danxx", "ARouter onLost 没有匹配到跳转路径");
                                }

                                @Override
                                public void onArrival(Postcard postcard) {
                                    Log.i("danxx", "ARouter onArrival 成功跳转");
                                }

                                @Override
                                public void onInterrupt(Postcard postcard) {
                                    Log.i("danxx", "ARouter onInterrupt 跳转被中断");
                                }
                            });
                    break;
                case R.id.toDynamic:
                    Log.i("danxx", "onClick toNews");
                    //跳转到ActivityDynamicGirls (模拟动态url)
                    ARouter.getInstance()
                            .build(ARouterPath.DynaGirlsListAty)
                            .withString("fullUrl", "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                            .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                            .navigation(ActivityMain.this, 3);
                    break;
            }
        }
    };

    public interface  ItemClick{
        void onClick(int id);
    }

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final GirlsViewModel model){
        //观察数据变化来刷新UI
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("danxx", "onActivityResult requestCode: "+requestCode);

    }
}
