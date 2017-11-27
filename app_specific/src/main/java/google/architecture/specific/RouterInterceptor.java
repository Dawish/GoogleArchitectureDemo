package google.architecture.specific;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import google.architecture.common.base.ARouterPath;

/**
 * Created by danxx on 2017/11/27.
 *
 * 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之前执行，多个拦截器会按优先级顺序依次执行
 *
 * priority就是优先级 可以设置多个级别的拦截器都活一次执行
 * 创建一个实现IInterceptor接口的类就是一个拦截器，不用做额外的配置了
 */
@Interceptor(priority = 8, name = "测试用拦截器")
public class RouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        if(postcard.getPath().contains(ARouterPath.GirlsListFgt)){
            Log.d("danxx", "拦截到向FragmentGirls跳转");
            //自定义处理
        }else {
            Log.d("danxx", "非拦截跳转执行path: "+postcard.getPath());
        }

        callback.onContinue(postcard);  // 处理完成，交还控制权
        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));   // 觉得有问题，中断路由流程
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
            // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        Log.d("danxx", "RouterInterceptor init");
    }
}
