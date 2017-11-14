package google.architecture.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import google.architecture.R;
import google.architecture.coremodel.datamodel.http.entities.FuliData;
import google.architecture.databinding.FuliItemBinding;

/**
 * Created by dxx on 2017/11/10.
 */

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.FuliViewHolder> {

    List<FuliData.ResultsBean> fuliList;
    FuliItemClickCallback fuliItemClickCallback;

    /**
     * 构造方法传入点击监听器
     * @param itemClickCallback
     */
    public FuliAdapter(@Nullable FuliItemClickCallback itemClickCallback) {
        fuliItemClickCallback = itemClickCallback;
    }

    public void setFuliList(List<FuliData.ResultsBean> list){
        if(fuliList == null){
            fuliList = list;
            notifyItemRangeInserted(0, fuliList.size());
        }else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return fuliList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    FuliData.ResultsBean oldData = fuliList.get(oldItemPosition);
                    FuliData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FuliData.ResultsBean oldData = fuliList.get(oldItemPosition);
                    FuliData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            fuliList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public FuliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FuliItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.fuli_item,
                        parent, false);
        binding.setCallback(fuliItemClickCallback);
        return new FuliViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FuliViewHolder holder, int position) {
        holder.binding.setFuliItem(fuliList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return fuliList == null ? 0 : fuliList.size();
    }

    static class FuliViewHolder extends RecyclerView.ViewHolder {
        FuliItemBinding binding;
        public FuliViewHolder(FuliItemBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
