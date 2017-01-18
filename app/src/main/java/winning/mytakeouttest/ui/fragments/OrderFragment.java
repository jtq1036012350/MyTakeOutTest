package winning.mytakeouttest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winning.mytakeouttest.R;


/**
 * Description: 订单
 * Author: Jiang
 * Date:   2017/1/17
 */
public class OrderFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_order, null);

        return view;
    }
}
