package com.example.we_in.storage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.we_in.R;

import java.util.ArrayList;
import java.util.List;

public class StorageFragment extends Fragment {
    ViewGroup viewGroup;

    List<Images> images;
    TextView tv_picnum;
    ViewPager viewPager;
    Adapter adapter;

    //variables using ViewPager2
    private int numPages;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_storage,container,false);

        images = new ArrayList<>();
        images.add(new Images(true,R.drawable.image1,"경복궁","사적 제117호. 도성의 북쪽에 있다고 하여 북궐(北闕)이라고도 불리었다. 조선왕조의 건립에 따라 창건되어 초기에 정궁으로 사용되었으나 임진왜란 때 전소된 후 오랫동안 폐허로 남아 있다가 조선 말기 고종 때 중건되어 잠시 궁궐로 이용되었다."));
        images.add(new Images(true,R.drawable.image2,"두번째","설명"));
        images.add(new Images(true,R.drawable.image3,"세번째","설명"));
        numPages = images.size();

        tv_picnum = viewGroup.findViewById(R.id.tv_picnum);
        tv_picnum.setText("/"+images.size());

        /*
        using ViewPager

        viewPager=viewGroup.findViewById(R.id.viewPager);

        adapter = new Adapter(images,this.getContext());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView page=viewGroup.findViewById(R.id.page);
                page.setText(position+1+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */


        /*
        ---------------using ViewPager2 start
        */

        viewPager2 = viewGroup.findViewById(R.id.viewPager);

        pagerAdapter = new ScreenSlidePagerAdapter(images, this.getActivity());
        viewPager2.setAdapter(pagerAdapter);

        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(1);
        int dpValue = 100;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager2.setClipToPadding(false);
        viewPager2.setPadding(margin, 0, margin, 0);

        /*
        ---------------using ViewPager2 end
        */

        return viewGroup;
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private List<Images> images;
        private LayoutInflater inflater;
        //private Context context;
        AnimatorSet front_anim;
        AnimatorSet back_anim;

        public ScreenSlidePagerAdapter(List<Images> images, FragmentActivity fa) {
            super(fa);
            this.images=images;
            //this.context=context;
        }

        @Override
        public Fragment createFragment(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getItemCount() {
            return numPages;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                view.setAlpha(0f);

            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0f);
            }
        }
    }
}
