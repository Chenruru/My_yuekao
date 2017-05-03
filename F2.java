package test.bwie.com.my_yuekao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.com.my_yuekao.utils.Url;
import test.bwie.com.my_yuekao.utils.Xutils;

/**
 * date: 2017/5/3
 * author:陈茹
 * 类的用途:
 */

public class F2 extends Fragment {

    private View f2;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        f2 = inflater.inflate(R.layout.layout_f2,null);


        return f2;
    }
}
