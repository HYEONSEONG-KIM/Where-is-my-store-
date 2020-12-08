package com.wims.whereismystore.Class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.wims.whereismystore.R;

public class SaleViewpagerAdapter extends PagerAdapter {
    LayoutInflater inflater;

    public SaleViewpagerAdapter(LayoutInflater inflater){
        this.inflater=inflater;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;//false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=null;
        view=inflater.inflate(R.layout.sale_view_pager_child_view,null);

        ImageView img=view.findViewById(R.id.saleViewPager_childImage);
        img.setImageResource(R.drawable.googleg_disabled_color_18);

        container.addView(view);
        return view;//super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }
}
