package winning.mytakeout;

import android.os.Bundle;

import winning.mytakeout.ui.base.BaseActivity;
import winning.mytakeouttest.R;

public class SellerDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_detail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishMyActivity();
    }
}
