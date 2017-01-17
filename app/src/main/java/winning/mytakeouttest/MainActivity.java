package winning.mytakeouttest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import winning.mytakeouttest.databinding.ActivityMainBinding;
import winning.mytakeouttest.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
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
                    chageUI(index);
                }
            });
        }
    }

    //通过点击的时候来改变UI
    private void chageUI(int index) {
        ToastUtil.showText(MainActivity.this, index + "");

    }


}
