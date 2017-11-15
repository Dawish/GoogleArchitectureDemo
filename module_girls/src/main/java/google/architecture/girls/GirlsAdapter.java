package google.architecture.girls;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.girls.databinding.GirlItemBinding;

/**
 * Created by dxx on 2017/11/10.
 */

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder> {

    List<GirlsData.ResultsBean> girlsList;
    GirlItemClickCallback girlItemClickCallback;

    /**
     * 构造方法传入点击监听器
     * @param itemClickCallback
     */
    public GirlsAdapter(@Nullable GirlItemClickCallback itemClickCallback) {
        girlItemClickCallback = itemClickCallback;
    }

    public void setGirlsList(final List<GirlsData.ResultsBean> list){
        if(girlsList == null){
            girlsList = list;
            notifyItemRangeInserted(0, girlsList.size());
        }else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return girlsList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData.ResultsBean oldData = girlsList.get(oldItemPosition);
                    GirlsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    GirlsData.ResultsBean oldData = girlsList.get(oldItemPosition);
                    GirlsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            girlsList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.girl_item,
                        parent, false);
        binding.setCallback(girlItemClickCallback);
        return new GirlsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
        holder.binding.setGirlsItem(girlsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return girlsList == null ? 0 : girlsList.size();
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {
        GirlItemBinding binding;
        public GirlsViewHolder(GirlItemBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
