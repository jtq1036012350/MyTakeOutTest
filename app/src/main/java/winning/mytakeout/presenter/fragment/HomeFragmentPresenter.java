package winning.mytakeout.presenter.fragment;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Call;
import winning.mytakeout.model.net.bean.HomeInfo;
import winning.mytakeout.model.net.bean.ResponseInfo;
import winning.mytakeout.presenter.BasePresenter;
import winning.mytakeout.ui.fragments.HomeFragment;

import static android.R.attr.data;


/**
 * Description: HomeFragmentPresenter
 * Author: Jiang
 * Date:   2017/1/19
 */
public class HomeFragmentPresenter extends BasePresenter {
    private HomeFragment homeFragment;

    public HomeFragmentPresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    private Call<ResponseInfo> call;

    @Override
    protected void failed(String msg) {
        homeFragment.failed(msg);
    }

    @Override
    protected void parserData(String data) {
        Gson gson = new Gson();
        HomeInfo info = gson.fromJson(data,HomeInfo.class);

        homeFragment.getAdapter().success(info);
    }

    //获取数据：
    public void getData() {
        Call<ResponseInfo> call = responseInfoAPI.home();
        call.enqueue(new CallbackAdapter());
    }

}
