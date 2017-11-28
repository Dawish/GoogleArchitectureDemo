package google.architecture.common.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.View;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by dxx on 2017/11/13.
 */

public class BindingUtils {

    @BindingAdapter("image")
    public static void loadImage(SimpleDraweeView image, String uri){
        if(image!=null){
            image.setImageURI(uri);
        }
    }

    @BindingAdapter("image")
    public static void loadImage(SimpleDraweeView image, int id){
        if(image!=null){
            image.setImageResource(id);
        }
    }

    @BindingAdapter("onNavigationItemSelectedListener")
    public static void setOnNavigationItemSelectedListener(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("viewPagerAdapter")
    public static void setViewPagerAdapter(ViewPager view, FragmentStatePagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
