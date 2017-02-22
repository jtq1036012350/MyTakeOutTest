package winning.mytakeout.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import winning.mytakeout.utils.ConstantUtil;
import winning.mytakeouttest.R;


/**
 * Description: Fragment的基类
 * Author: Jiang
 * Date:   2017/1/17
 */
public class BaseFragment extends Fragment {
    public BaseActivity.ForResultCallBack forResultCallBack;
    private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
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
    public void startMyActivityForResult(Class<?> cls, Bundle bundle, BaseActivity.ForResultCallBack forResultCallBack) {
        this.forResultCallBack = forResultCallBack;
        Intent intent = new Intent(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, ConstantUtil.FORRESULT);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    //封装startActivityForResult(这里为计量任务准备的，那里不知道为何会调用两次onSuccessScan)
    public void startMyActivityForResultClearTop(Class<?> cls, Bundle bundle, BaseActivity.ForResultCallBack forResultCallBack) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
