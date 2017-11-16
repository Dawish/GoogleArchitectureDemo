package google.architecture.universal;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import google.architecture.common.base.BaseActivity;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.universal.databinding.ActivityMainBinding;

public class ActivityMain extends BaseActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
