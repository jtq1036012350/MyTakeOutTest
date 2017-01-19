package winning.mytakeout.presenter.fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import winning.mytakeout.model.net.bean.ResponseInfo;
import winning.mytakeout.presenter.BasePresenter;


/**
 * Description: (这里用一句话描述这个类的作用)
 * Author: Jiang
 * Date:   2017/1/19
 */
public class HomeFragmentPresenter extends BasePresenter {

    private Call<ResponseInfo> call;

    @Override
    protected void failed(String msg) {

    }

    @Override
    protected void parserData(String data) {

    }

    //获取数据：
    public void getData() {
        Call<ResponseInfo> call =  responseInfoAPI.home();
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {

            }
        });
    }
}
