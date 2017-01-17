package winning.mytakeouttest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import winning.mytakeouttest.base.BaseActivity;
import winning.mytakeouttest.databinding.ActivityMainBinding;
import winning.mytakeouttest.fragments.HomeFragment;
import winning.mytakeouttest.fragments.MeFragment;
import winning.mytakeouttest.fragments.MoreFragment;
import winning.mytakeouttest.fragments.OrderFragment;

/**
 * 主界面Activity
 */
public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding = null;

    //用来存放主界面的Fragemnt
    private ArrayList<Fragment> fragments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //初始化数据
        initDatas();
        //初始化布局
        initViews();
    }


    private void initDatas() {
        fragments = new ArrayList<>();
        //添加Fragnment
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MeFragment());
        fragments.add(new MoreFragment());

        changeFragments(0);
        //默认选择第一个条目
        setEnable(binding.mainBottomeSwitcherContainer.getChildAt(0), false);
    }

    //初始化布局
    private void initViews() {
        int count = binding.mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            FrameLayout childAt = (FrameLayout) binding.mainBottomeSwitcherContainer.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = binding.mainBottomeSwitcherContainer.indexOfChild(view);
                    changeUI(index);
                    changeFragments(index);
                }


            });
        }
    }

    //切换Fragment
    private void changeFragments(int index) {
        Fragment fragment = fragments.get(index);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
    }

    //通过点击的时候来改变UI
    private void changeUI(int index) {
//        ToastUtil.showText(MainActivity.this, index + "");
        int childCount = binding.mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == index) {
                setEnable(binding.mainBottomeSwitcherContainer.getChildAt(i), false);
            } else {
                setEnable(binding.mainBottomeSwitcherContainer.getChildAt(i), true);
            }
        }
    }

    /**
     * 将每个Item中的所用控件状态一同改变
     * 由于我们处理一个通用的代码，那么Item可能会有很多层，所以我们需要使用递归
     *
     * @param item
     * @param b
     */
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);
        if (item instanceof ViewGroup) {
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i), b);
            }
        }
    }


}
