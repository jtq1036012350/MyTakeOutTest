package winning.mytakeout.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Description: 商品详情页面的Adapter
 * Author: Jiang
 * Date:   2017/1/22
 */
public class MyVPSellerDetailAdapter extends FragmentPagerAdapter {
    private String[] tittles;
    private ArrayList<Fragment> fragments;

    public MyVPSellerDetailAdapter(FragmentManager fm, String[] tittles, ArrayList<Fragment> fragments) {
        super(fm);
        this.tittles = tittles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = fragments.get(0);
                break;
            case 1:
                fragment = fragments.get(1);
                break;
            case 2:
                fragment = fragments.get(2);
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tittles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tittles[position];
    }
}
