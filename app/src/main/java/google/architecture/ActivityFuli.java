package google.architecture;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import google.architecture.databinding.ActivityFuliBinding;
import google.architecture.model.http.entities.FuliData;
import google.architecture.ui.FuliAdapter;
import google.architecture.ui.FuliItemClickCallback;
import google.architecture.viewmodel.FuliViewModel;

public class ActivityFuli extends AppCompatActivity {
    ActivityFuliBinding binding;
    FuliAdapter         fuliAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("danxx", "onCreate onCreate");
        binding = DataBindingUtil.setContentView(ActivityFuli.this, R.layout.activity_fuli);

        FuliViewModel model = new FuliViewModel(ActivityFuli.this.getApplication());

        fuliAdapter = new FuliAdapter(mFuliItemClickCallback);

        binding.fuliList.setAdapter(fuliAdapter);

        subscribeToModel(model);


    }

    private final FuliItemClickCallback mFuliItemClickCallback = new FuliItemClickCallback() {
        @Override
        public void onClick(FuliData.ResultsBean comment) {
            Toast.makeText(ActivityFuli.this, comment.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final FuliViewModel model){
        model.getLiveObservableData().observe(this, new Observer<FuliData>() {
            @Override
            public void onChanged(@Nullable FuliData fuliData) {
                Log.i("danxx", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(fuliData);
                fuliAdapter.setFuliList(fuliData.getResults());
            }
        });
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
