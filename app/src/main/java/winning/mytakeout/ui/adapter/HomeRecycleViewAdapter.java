package winning.mytakeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import winning.mytakeout.MyApplication;
import winning.mytakeout.model.net.bean.Head;
import winning.mytakeout.model.net.bean.HomeInfo;
import winning.mytakeout.model.net.bean.HomeItem;
import winning.mytakeouttest.R;

/**
 * Description: HomeRecycleViewAdapter
 * Author: Jiang
 * Date:   2017/1/18
 */
public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //数据集合
    private HomeInfo data;

    //三种类型的数据
    private final int TYPE_HEAD = 0;//头布局的展示
    private final int TYPE_SELLER = 1;//卖家列表的展示
    private final int TYPE_RECOMMEND = 2;//推荐列表的展示

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (position == 0) {
            type = TYPE_HEAD;
        } else {
            HomeItem homeItem = data.body.get(position - 1);
            type = homeItem.type == 0 ? TYPE_SELLER : TYPE_RECOMMEND;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEAD:
                holder = new HeadViewHolder(View.inflate(MyApplication.getContext(), R.layout.item_title, null));
                break;
            case TYPE_SELLER:
                holder = new SellerViewHolder(View.inflate(MyApplication.getContext(), R.layout.item_seller, null));
                break;
            case TYPE_RECOMMEND:
                holder = new RecommandViewHolder(View.inflate(MyApplication.getContext(), R.layout.item_division, null));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_HEAD:
                ((HeadViewHolder) holder).setData(data.head);
                break;
            case TYPE_SELLER:
                ((SellerViewHolder) holder).setData(data.body.get(position - 1));
                break;
            case TYPE_RECOMMEND:
                ((RecommandViewHolder) holder).setData(data.body.get(position - 1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (data == null) {
            count = 0;
        } else {
            count = data.body.size() + 1;
        }
        return count;
    }

    public void setData(HomeInfo info) {
        data = info;
        notifyDataSetChanged();
    }

    //头布局
    class HeadViewHolder extends RecyclerView.ViewHolder {
        private Head data;

        public HeadViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(Head data) {
            this.data = data;
        }
    }

    //卖家布局
    class SellerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title = null;
        private HomeItem data;

        public SellerViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(HomeItem data) {
            this.data = data;
            tv_title.setText(data.seller.name);
        }
    }

    //推荐布局
    class RecommandViewHolder extends RecyclerView.ViewHolder {
        private HomeItem data;

        public RecommandViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(HomeItem data) {
            this.data = data;
        }
    }
}
