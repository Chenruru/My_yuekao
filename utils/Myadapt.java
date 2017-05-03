package test.bwie.com.my_yuekao.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import test.bwie.com.my_yuekao.R;
import test.bwie.com.my_yuekao.bean.ImageBean;


/**
 * date: 2017/4/25
 * author:陈茹
 * 类的用途:
 */

public class Myadapt extends BaseAdapter {
    private List<ImageBean.ResultBean.BrandsBean> list;
    private Context context;

    public Myadapt( List<ImageBean.ResultBean.BrandsBean> list, Context context) {
        this.list= list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.l_item, null);
      //  ImageView imagevi = (ImageView) convertView.findViewById(R.id.imagevi);
        TextView text_title = (TextView) convertView.findViewById(R.id.text_biaoti);
        TextView text_neirong = (TextView) convertView.findViewById(R.id.text_xinxi);
        ImageView imageview = (ImageView) convertView.findViewById(R.id.imageview);


        text_title.setText(list.get(position).getTitle());
        text_neirong.setText(list.get(position).getProducts().get(position).getName());


        DisplayImageOptions option = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnLoading(R.mipmap.ic_launcher).build();
        ImageLoader.getInstance().displayImage(list.get(position).getPic(), imageview, option);
      //  ImageLoader.getInstance().displayImage(list.get(position).getProducts().get(position).getPic(), imagevi, option);
        return convertView;
    }
}