package google.architecture.girls;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import google.architecture.common.base.BaseActivity;
import google.architecture.coremodel.datamodel.http.ApiConstants;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.viewmodel.BaseViewModel;
import google.architecture.coremodel.viewmodel.DynamicGirlsViewModel;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.girls.databinding.ActivityGirlsBinding;

/**
 * Created by dxx on 2017/11/20.
 */
@Route(path = "/dynamic/girls/list")
public class ActivityDynamicGirls extends BaseActivity {
    @Autowired(name = "fullUrl")
    public String fullUrl;
    GirlsAdapter            girlsAdapter;
    ActivityGirlsBinding activityGirlsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_ActivityDynamicGirls");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityGirlsBinding = DataBindingUtil.setContentView(ActivityDynamicGirls.this,R.layout.activity_girls);

        Log.i("danxx", "fullUrl-->"+fullUrl);

        DynamicGirlsViewModel dynamicGirlsViewModel = new DynamicGirlsViewModel(ActivityDynamicGirls.this.getApplication(), "/api/data/%E7%A6%8F%E5%88%A9/20/1");

        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        activityGirlsBinding.girlsList.setAdapter(girlsAdapter);
        subscribeToModel(dynamicGirlsViewModel);

    }

    GirlItemClickCallback   girlItemClickCallback = new GirlItemClickCallback() {
        @Override
        public void onClick(GirlsData.ResultsBean fuliItem) {
            Toast.makeText(ActivityDynamicGirls.this, fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final BaseViewModel model){
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(this, new Observer<GirlsData>() {
            @Override
            public void onChanged(@Nullable GirlsData girlsData) {
                Log.i("danxx", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(girlsData);
                girlsAdapter.setGirlsList(girlsData.getResults());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
