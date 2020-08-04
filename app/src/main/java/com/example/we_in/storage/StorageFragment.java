package com.example.we_in.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.we_in.R;

import java.util.ArrayList;
import java.util.List;

public class StorageFragment extends Fragment {
    ViewGroup viewGroup;
    ViewPager2 viewPager2;
    Adapter adapter;

    List<Images> images;
    TextView tv_picnum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_storage,container,false);

        images = new ArrayList<>();
        images.add(new Images(true,R.drawable.image1,"경복궁","사적 제117호. 도성의 북쪽에 있다고 하여 북궐(北闕)이라고도 불리었다. 조선왕조의 건립에 따라 창건되어 초기에 정궁으로 사용되었으나 임진왜란 때 전소된 후 오랫동안 폐허로 남아 있다가 조선 말기 고종 때 중건되어 잠시 궁궐로 이용되었다."));
        images.add(new Images(true,R.drawable.image2,"두번째","설명"));
        images.add(new Images(true,R.drawable.image3,"세번째","설명"));

        tv_picnum = viewGroup.findViewById(R.id.tv_picnum);
        tv_picnum.setText("/"+images.size());

        viewPager2 = viewGroup.findViewById(R.id.viewPager);
        adapter=new Adapter(images,this.getContext());
        viewPager2.setAdapter(adapter);

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40)); // 카드 사이 간격
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                float r= 1 - Math.abs(position);
                view.setScaleY(0.85f + r*0.15f);
                view.setAlpha(0.35f + r*0.65f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                TextView page=viewGroup.findViewById(R.id.page);
                page.setText((position+1)+"");
            }
        });

        return viewGroup;
    }

}
