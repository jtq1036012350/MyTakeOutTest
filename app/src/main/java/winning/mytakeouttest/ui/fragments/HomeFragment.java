package winning.mytakeouttest.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winning.mytakeouttest.R;
import winning.mytakeouttest.databinding.FragmentHomeBinding;
import winning.mytakeouttest.ui.adapter.HomeRecycleViewAdapter;
import winning.mytakeouttest.ui.base.BaseFragment;


/**
 * Description: 首页
 * Author: Jiang
 * Date:   2017/1/17
 */
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;

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
        binding.homeRecycleview.setAdapter(new HomeRecycleViewAdapter());
        binding.homeRecycleview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false ));
    }
}
