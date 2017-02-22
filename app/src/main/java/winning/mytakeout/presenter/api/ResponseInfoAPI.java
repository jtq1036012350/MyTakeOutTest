package winning.mytakeout.presenter.api;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import winning.mytakeout.model.net.bean.ResponseInfo;
import winning.mytakeout.utils.ConstantUtil;

/**
 * 对请求方式和请求参数的封装
 */
public interface ResponseInfoAPI {
    /**
     * 用户登陆:/login?username="itheima"&password="bj"
     */
//    @GET(ConstantUtil.PATH+ConstantUtil.LOGIN)

    /**
     * 传统方式登陆
     * @param username
     * @param password
     * @return
     */
    @GET(ConstantUtil.LOGIN)
    Call<ResponseInfo> login(
            @Query("username")// 参数的名字
                    String username, // 该参数的值
            @Query("password")
                    String password);

    /**
     * 短信验证的登陆
     * @param phone
     * @param type
     * @return
     */
    @GET(ConstantUtil.LOGIN)
    Call<ResponseInfo> login(@Query("phone") String phone,
                             @Query("type") int type);

    /**
     * 获取首页数据
     * @return
     */
    @GET(ConstantUtil.HOME)
    Call<ResponseInfo> home();

    /**
     * 获取商品数据
     * @param sellerId（商家标识）
     * @return
     */
    @GET(ConstantUtil.GOODS)
    Call<ResponseInfo> goods(@Query("sellerId") long sellerId);

    @GET(ConstantUtil.ADDRESS)
    Call<ResponseInfo> address(@Query("userId") int userId);

    @FormUrlEncoded
    @POST(ConstantUtil.ORDER)
    Call<ResponseInfo> creatOrder(@Field("orderOverview") String json);


    @GET(ConstantUtil.PAY)
    Call<ResponseInfo> payment(@Query("orderId") String orderId);
    @GET(ConstantUtil.ORDER)
    Call<ResponseInfo> orderList(@Query("userId") int userid);
}
