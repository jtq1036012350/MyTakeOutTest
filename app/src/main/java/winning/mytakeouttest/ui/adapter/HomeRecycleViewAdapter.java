package winning.mytakeouttest.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import winning.mytakeouttest.R;

/**
 * Description: HomeRecycleViewAdapter
 * Author: Jiang
 * Date:   2017/1/18
 */
public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_homefragment_layout, null);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommonViewHolder) holder).tv.setText("position" + position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView tv = null;

        public CommonViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

    }
}
