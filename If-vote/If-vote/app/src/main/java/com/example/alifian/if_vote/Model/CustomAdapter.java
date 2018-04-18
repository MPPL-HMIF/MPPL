package com.example.alifian.if_vote.Model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alifian.if_vote.R;

import org.w3c.dom.Text;

/**
 * Created by ALIFIAN on 15/04/2018.
 */

public class CustomAdapter extends PagerAdapter{
    private int[] img = {R.drawable.ss1,R.drawable.ss2};
    private LayoutInflater inflater;
    private Context ctx;

    public CustomAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.swipe,container,false);
        ImageView imgs = (ImageView)v.findViewById(R.id.imageview);
        TextView tv1 = (TextView)v.findViewById(R.id.textview);
        imgs.setImageResource(img[position]);
        tv1.setText("Image : "+position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
