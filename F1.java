package test.bwie.com.my_yuekao;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import test.bwie.com.my_yuekao.utils.Url;
import test.bwie.com.my_yuekao.utils.Xutils;

/**
 * date: 2017/5/3
 * author:陈茹
 * 类的用途:
 */

public class F1 extends Fragment {
    @Nullable
    private ViewPager viewpager;

    private ListView listview;

    private View f1;
    private int oldPosition = 0;
    private String imageUrl[]=new String[]
            {"http://a.hiphotos.baidu.com/zhidao/pic/item/f2deb48f8c5494ee203bf98c2cf5e0fe99257e0e.jpg",
            "http://www.babybuy100.com/Storage/master/product/thumbs310/310_201608211825117862720.jpg",
                    "http://www.babybuy100.com/Storage/master/product/thumbs310/310_201608211815272236280.jpg"};
    private List<ImageView> imgeList;
    private ScheduledExecutorService ses;
    private int currentPosition = 0;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            viewpager.setCurrentItem(currentPosition);

        };
    };
    private List<View> dotList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        f1 = inflater.inflate(R.layout.layout_f1,null);
        initview();
        Xutils xutils=new Xutils(getActivity(),listview,viewpager);
        xutils.getXutil(Url.uri);
        initData();
        return f1;
    }

    @Override
    public void onResume() {
        super.onResume();
        //得到一个定时执行器实例
        ses = Executors.newSingleThreadScheduledExecutor();
        //参数1：你要执行一个定时任务
        //参数2：第一次启动延迟的时间
        //参数3：任务执行的间隔时间
        //参数4：时间单位
        ses.scheduleAtFixedRate(new PagerTask(), 2, 2, TimeUnit.SECONDS);

    }


    //页面切换的任务
    class PagerTask implements Runnable{

        @Override
        public void run() {
            currentPosition+=1;
            //vp.setCurrentItem(item)
            handler.sendEmptyMessage(0);
        }

    }

    private void initData() {
        imgeList = new ArrayList<ImageView>();

        //根据图片地址个数生成ImageView
        for (int i = 0; i < imageUrl.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.getInstance().displayImage(imageUrl[i],imageView);
            imgeList.add(imageView);
        }

        //初始化圆点
        dotList = new ArrayList<View>();
        dotList.add(f1.findViewById(R.id.dot1));
        dotList.add(f1.findViewById(R.id.dot2));
        dotList.add(f1.findViewById(R.id.dot3));

    }

    private void initview() {
        viewpager = (ViewPager) f1.findViewById(R.id.viewpager);
        listview = (ListView) f1.findViewById(R.id.listview);
        viewpager.setAdapter(new MyPagerAdapter());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                dotList.get(position % imgeList.size()).setBackgroundResource(R.drawable.dot_focus);
                //更改前一个点的选中状态
                dotList.get(oldPosition % imgeList.size()).setBackgroundResource(R.drawable.dot_normal);

                //更新圆点位置
                oldPosition = position;//0,1,2
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    //ViewPager的适配器
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override//产生一个条目
        public Object instantiateItem(ViewGroup container, int position) {

            final ImageView imageView = imgeList.get(position % imgeList.size());
            container.addView(imageView);

            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(imgeList.get(position % imgeList.size()));

        }

    }
}
