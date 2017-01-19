package winning.mytakeout.ui.fragments;

import android.animation.ArgbEvaluator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import winning.mytakeout.dagger.component.DaggerHomeFragmentComponent;
import winning.mytakeout.dagger.component.HomeFragmentComponent;
import winning.mytakeout.dagger.module.fragment.HomeFragmentModule;
import winning.mytakeout.presenter.fragment.HomeFragmentPresenter;
import winning.mytakeout.ui.adapter.HomeRecycleViewAdapter;
import winning.mytakeout.ui.base.BaseFragment;
import winning.mytakeout.utils.LogUtil;
import winning.mytakeout.utils.ToastUtil;
import winning.mytakeouttest.R;
import winning.mytakeouttest.databinding.FragmentHomeBinding;


/**
 * Description: 首页
 * Author: Jiang
 * Date:   2017/1/17
 */
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    //当前滑动的总距离
    private int sumY = 0;
    //滑动渐变的临界值
    private float duration = 150.0f;
    //颜色的计算器
    private ArgbEvaluator argbEvaluator;

    @Inject
    HomeFragmentPresenter homeFragmentPresenter;

    private HomeRecycleViewAdapter homeRecycleViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerHomeFragmentComponent.Builder builder = DaggerHomeFragmentComponent
                .builder();
        builder.homeFragmentModule(new HomeFragmentModule(this));
        HomeFragmentComponent component = builder.build();
        component.in(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        homeFragmentPresenter.getData();
        // 显示滚动条
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeRecycleViewAdapter = new HomeRecycleViewAdapter();
        binding.homeRecycleview.setAdapter(homeRecycleViewAdapter);
        binding.homeRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //RecycleView的滑动监听(用来处理顶部栏的透明度)
        binding.homeRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                LogUtil.d("infotest", sumY / duration + "");
                argbEvaluator = new ArgbEvaluator();
                int bgColor = 0x553190E8;
                if (sumY < 0) {
                    bgColor = 0x553190E8;
                } else if (sumY > 150) {
                    bgColor = 0xFF3190E8;
                } else {
                    bgColor = (int) argbEvaluator.evaluate(sumY / duration, 0x553190E8, 0xFF3190E8);
                }
                binding.llTitleContainer.setBackgroundColor(bgColor);
            }
        });

    }

    public void failed(String data) {
        ToastUtil.showText(getContext(), data);
    }

    public HomeRecycleViewAdapter getAdapter() {
        return homeRecycleViewAdapter;
    }
}
