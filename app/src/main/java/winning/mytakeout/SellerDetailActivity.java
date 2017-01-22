package winning.mytakeout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;

import winning.mytakeout.ui.adapter.MyVPSellerDetailAdapter;
import winning.mytakeout.ui.base.BaseActivity;
import winning.mytakeout.ui.fragments.vp_fragments.EvaluateFragment;
import winning.mytakeout.ui.fragments.vp_fragments.GoodsFragment;
import winning.mytakeout.ui.fragments.vp_fragments.SellersFragment;
import winning.mytakeouttest.R;
import winning.mytakeouttest.databinding.ActivitySellerDetailBinding;

public class SellerDetailActivity extends BaseActivity {
    private ActivitySellerDetailBinding binding;

    private String[] tittles = new String[]{"商品", "评价", "商家"};

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_seller_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seller_detail, null);
        initViews();
        initDatas();
    }

    private void initViews() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        String seller_id = bundle.getString("seller_id");
        String name = bundle.getString("name");
        binding.toolbar.setTitle(name);

        setSupportActionBar(binding.toolbar);// 替换ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragments = new ArrayList<>();
        fragments.add(new GoodsFragment());
        fragments.add(new EvaluateFragment());
        fragments.add(new SellersFragment());

        //设置TabLayoput以及ViewPager
        binding.vp.setAdapter(new MyVPSellerDetailAdapter(getSupportFragmentManager(), tittles, fragments));
        binding.tabs.setupWithViewPager(binding.vp);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishMyActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDatas() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishMyActivity();
    }
}
