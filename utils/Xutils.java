package test.bwie.com.my_yuekao.utils;

        import android.content.Context;
        import android.support.v4.view.ViewPager;
        import android.widget.ListView;
        import com.google.gson.Gson;
        import com.nostra13.universalimageloader.core.ImageLoader;

        import org.xutils.common.Callback;
        import org.xutils.http.RequestParams;
        import org.xutils.x;
        import java.util.List;
        import test.bwie.com.my_yuekao.bean.ImageBean;

/**
 * date: 2017/5/2
 * author:陈茹
 * 类的用途:
 */

public class Xutils {
        private ListView mLv;
         private  ViewPager viewpager;
        private Context mContext;
    private List<ImageBean.ResultBean.BrandsBean> list;

        public Xutils(Context context,ListView lv,ViewPager view) {
                mContext = context;
                mLv = lv;
               viewpager=view;

        }


        public void getXutil(String uri) {
                RequestParams params = new RequestParams(Url.uri);
                //params.addQueryStringParameter("uri", uri);
                x.http().get(params, new Callback.CommonCallback<String>() {



                    @Override
                        public void onSuccess(String result) {

                             Gson gson=new Gson();
                             ImageBean imageBean = gson.fromJson(result, ImageBean.class);

                             list = imageBean.getResult().getBrands();


                        //listview适配器
                        Myadapt myadapt=new Myadapt(list,mContext);
                        mLv.setAdapter(myadapt);


                    }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                });
        }



}
