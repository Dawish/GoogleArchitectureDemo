package google.architecture.girls;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import google.architecture.common.base.BaseActivity;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.girls.databinding.ActivityGirlsBinding;

@Route(path = "/girls/list")
public class ActivityGirls extends BaseActivity {

    GirlsAdapter            girlsAdapter;
    ActivityGirlsBinding    activityGirlsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_ActivityGirls");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityGirlsBinding = DataBindingUtil.setContentView(ActivityGirls.this,R.layout.activity_girls);
        GirlsViewModel girlsViewModel = new GirlsViewModel(ActivityGirls.this.getApplication());
        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        activityGirlsBinding.girlsList.setAdapter(girlsAdapter);
        subscribeToModel(girlsViewModel);

    }

    GirlItemClickCallback   girlItemClickCallback = new GirlItemClickCallback() {
        @Override
        public void onClick(GirlsData.ResultsBean fuliItem) {
            Toast.makeText(ActivityGirls.this, fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final GirlsViewModel model){
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
