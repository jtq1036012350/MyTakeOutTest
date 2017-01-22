package winning.mytakeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;

import winning.mytakeout.MyApplication;
import winning.mytakeout.model.net.bean.Head;
import winning.mytakeout.model.net.bean.HomeInfo;
import winning.mytakeout.model.net.bean.HomeItem;
import winning.mytakeout.model.net.bean.Promotion;
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
        //这里根据条目位置返回当前的类型
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
        //由于木有相关的viewType参数，只能通过方法来获取了
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
        private SliderLayout slider;
        private LinearLayout catetory_container;

        public HeadViewHolder(View itemView) {
            super(itemView);
            slider = (SliderLayout) itemView.findViewById(R.id.slider);
            catetory_container = (LinearLayout) itemView.findViewById(R.id.catetory_container);
        }

        public void setData(Head data) {
            this.data = data;
            //顶部的轮播图
            slider.removeAllSliders();
            if (data != null && data.promotionList.size() > 0) {
                for (Promotion temp : data.promotionList) {
                    TextSliderView textSliderView = new TextSliderView(MyApplication.getContext());
                    textSliderView.image(temp.pic);
                    textSliderView.description(temp.info);
                    slider.addSlider(textSliderView);
                }
            }
            //下面的分类图表
            if (data != null && data.categorieList.size() > 0) {
                View view = null;
                for (int i = 0; i < data.categorieList.size(); i++) {
                    if (i % 2 == 0) {
                        view = View.inflate(MyApplication.getContext(), R.layout.item_home_head_category, null);
                        Picasso.with(MyApplication.getContext()).load(data.categorieList.get(i).pic).into((ImageView) view.findViewById(R.id.top_iv));
                        ((TextView) view.findViewById(R.id.top_tv)).setText(data.categorieList.get(i).name);
                        catetory_container.addView(view);
                    }
                    if (i % 2 != 0) {
                        Picasso.with(MyApplication.getContext()).load(data.categorieList.get(i).pic).into((ImageView) view.findViewById(R.id.bottom_iv));
                        ((TextView) view.findViewById(R.id.bottom_tv)).setText(data.categorieList.get(i).name);
                    }
                }
            }
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
