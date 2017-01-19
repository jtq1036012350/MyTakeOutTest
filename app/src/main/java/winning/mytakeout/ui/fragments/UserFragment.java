package winning.mytakeout.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winning.mytakeouttest.R;
import winning.mytakeout.ui.base.BaseFragment;


/**
 * Description: 个人
 * Author: Jiang
 * Date:   2017/1/17
 */
public class UserFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_me, null);

        return view;
    }
}
