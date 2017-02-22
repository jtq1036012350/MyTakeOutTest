package winning.mytakeout.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import winning.mytakeout.utils.ConstantUtil;
import winning.mytakeouttest.R;

/**
 * 父类actvity
 * Created by jiang on 2016/9/23.
 */

public class BaseActivity extends AppCompatActivity {
    private BaseActivity mActivity;
    public ForResultCallBack forResultCallBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    //监听按下了返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finishMyActivity();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 提供统一的关闭方式
     * <p>
     * （另一个Activity）
     */
    public void finishMyActivity() {
        mActivity.finish();
        mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    /**
     * 提供统一的启动方式
     *
     * @param cls，intent（另一个Activity，intent携带的数据）
     */
    public void startMyActivity(Class<?> cls, Bundle mBundle) {
        Intent intent = new Intent(mActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    //封装startActivityForResult
    public void startMyActivityForResult(Class<?> cls, Bundle bundle, ForResultCallBack forResultCallBack) {
        this.forResultCallBack = forResultCallBack;
        Intent intent = new Intent(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, ConstantUtil.FORRESULT);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    //封装startActivityForResult(这里为计量任务准备的，那里不知道为何会调用两次onSuccessScan)
    public void startMyActivityForResultClearTop(Class<?> cls, Bundle bundle, ForResultCallBack forResultCallBack) {
        this.forResultCallBack = forResultCallBack;
        Intent intent = new Intent(mActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, ConstantUtil.FORRESULT);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    //将数据传递给回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case ConstantUtil.FORRESULT:
                forResultCallBack.forResult(data);
                break;
        }
    }

    public interface ForResultCallBack {
        public void forResult(Intent data);
    }
}
