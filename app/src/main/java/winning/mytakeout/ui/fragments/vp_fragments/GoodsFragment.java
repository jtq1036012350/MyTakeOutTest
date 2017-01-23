package winning.mytakeout.ui.fragments.vp_fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import winning.mytakeout.MyApplication;
import winning.mytakeout.ui.base.BaseFragment;
import winning.mytakeouttest.R;
import winning.mytakeouttest.databinding.FragmentVpGoodsBinding;


/**
 * Description: 商品
 * Author: Jiang
 * Date:   2017/1/17
 */
public class GoodsFragment extends BaseFragment {

    private FragmentVpGoodsBinding binding;

    private int selectedPosition = 0;

    class Data {
        String info;
        int headId;
        int headIndex;
    }

    class Head {
        String info;
        //对应指定头的第一个元素下标
        int groupFirstIndex;
    }

    ArrayList<Data> datas = new ArrayList<>();
    ArrayList<Head> heads = new ArrayList<>();

    void setData() {
//        for (int hi = 0; hi < 10; hi++) {
//            Head head = new Head();
//            head.info = "头" + hi;
//            heads.add(head);
//        }
//
//        for (int di = 0; di < 100; di++) {
//            Data data = new Data();
//            data.info = "测试数据" + di;
//            datas.add(data);
//        }
        for (int hi = 0; hi < 10; hi++) {
            Head head = new Head();
            for (int di = 0; di < 10; di++) {
                Data data = new Data();
                data.info = "普通" + di;
                data.headId = hi;
                data.headIndex = hi;
                datas.add(data);
                if (di == 0) {
                    head.groupFirstIndex = datas.size();
                }
            }

            head.info = "头部" + hi;
            heads.add(head);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = View.inflate(getActivity(), R.layout.fragment_vp_goods, null);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vp_goods, container, false);
        setData();
        final MyLeftAdapter myLeftAdapter = new MyLeftAdapter();
        binding.shl.setAdapter(new MyGroupAdapter());
        binding.lv.setAdapter(myLeftAdapter);

        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                binding.lv.setSelection(position);
                myLeftAdapter.notifyChange();
                binding.shl.setSelection(heads.get(position).groupFirstIndex);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class MyLeftAdapter extends BaseAdapter {

        public void notifyChange() {
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return heads.size();
        }

        @Override
        public Object getItem(int position) {
            return heads.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Head head = heads.get(position);
            TextView tv = new TextView(MyApplication.getContext());
            tv.setText("左边" + head.info);

            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));

            tv.setTextSize(16);
            tv.setGravity(Gravity.CENTER);

            if (position == selectedPosition) {
                tv.setBackgroundColor(Color.BLUE);
            } else {
                tv.setBackgroundColor(Color.WHITE);
            }
            return tv;
        }
    }

    class MyGroupAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            Data data = datas.get(position);
            Head head = heads.get(data.headIndex);

            TextView tv = new TextView(MyApplication.getContext());
            tv.setText("头布局" + head.info);
            tv.setBackgroundColor(Color.BLUE);
            return tv;
        }

        @Override
        public long getHeaderId(int position) {
            Data data = datas.get(position);
            return data.headId;
        }

        /**
         * 上面是头对应的项目
         * ---------------------------------------------------------------------
         **/
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MyApplication.getContext());
            tv.setText("子布局");
            tv.setBackgroundColor(Color.GRAY);
            tv.setText(datas.get(position).info);
            return tv;
        }
    }
}
