package google.architecture.news;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseActivity;
import google.architecture.coremodel.datamodel.http.entities.NewsData;
import google.architecture.coremodel.viewmodel.NewsViewModel;
import android.arch.lifecycle.ViewModelProviders;
import google.architecture.news.databinding.ActivityNewsBinding;

@Route(path = ARouterPath.NewsListAty)
public class ActivityNews extends BaseActivity {

    NewsAdapter newsAdapter;
    ActivityNewsBinding activityNewsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_ActivityNews");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityNewsBinding = DataBindingUtil.setContentView(ActivityNews.this,R.layout.activity_news);
//        NewsViewModel newsViewModel = new NewsViewModel(ActivityNews.this.getApplication());
        /**使用ViewModelProvider才可以使ViewModel跟Activity的生命周期关联一起*/
        NewsViewModel newsViewModel =
                ViewModelProviders.of(ActivityNews.this).get(NewsViewModel.class);
        newsAdapter = new NewsAdapter(girlItemClickCallback);
        activityNewsBinding.newsList.setAdapter(newsAdapter);
        subscribeToModel(newsViewModel);

    }

    NewsItemClickCallback girlItemClickCallback = new NewsItemClickCallback() {
        @Override
        public void onClick(NewsData.ResultsBean fuliItem) {
            Toast.makeText(ActivityNews.this, fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final NewsViewModel model){
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(this, new Observer<NewsData>() {
            @Override
            public void onChanged(@Nullable NewsData newsData) {
                Log.i("danxx", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(newsData);
                newsAdapter.setGirlsList(newsData.getResults());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
