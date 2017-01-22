package winning.mytakeout.ui.fragments.vp_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winning.mytakeout.ui.base.BaseFragment;
import winning.mytakeouttest.R;


/**
 * Description: 商家
 * Author: Jiang
 * Date:   2017/1/17
 */
public class SellersFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_vp_sellers, null);

        return view;
    }
}
