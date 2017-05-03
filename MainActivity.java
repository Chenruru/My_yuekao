package test.bwie.com.my_yuekao;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.my_yuekao.utils.Url;
import test.bwie.com.my_yuekao.utils.Xutils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button button1;
    private Button button2;
    private ViewPager viewp;
    private List<Fragment> list;
    private Button[] arr;
    private int posion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initview();
        initdata();
        setjianting();
        setcolor(posion);
        viewp.setAdapter(new Mydata(getSupportFragmentManager()));
        viewp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                  //viewpager滑动
                    setcolor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setcolor(int posion) {
        for (int i = 0; i <arr.length ; i++) {
            if (i==posion){
                arr[i].setBackgroundColor(Color.GREEN);
            }else {
                arr[i].setBackgroundColor(Color.WHITE);
            }
        }
    }

    private void setjianting() {
        button1.setOnClickListener((View.OnClickListener) this);
        button2.setOnClickListener((View.OnClickListener) this);
    }

    private void initdata() {
        list = new ArrayList<>();
        list.add(new F1());
        list.add(new F2());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
               viewp.setCurrentItem(0);
                setcolor(0);
                break;
            case R.id.button2:
                viewp.setCurrentItem(1);
                setcolor(1);
                break;
        }
    }

    class Mydata extends FragmentPagerAdapter{


        public Mydata(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    private void initview() {

        viewp = (ViewPager) findViewById(R.id.viewp);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        arr = new Button[]{button1,button2};
    }


}
