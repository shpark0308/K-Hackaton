package com.example.we_in.storage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.we_in.R;

import java.util.List;

public class Adapter extends PagerAdapter {
    private List<Images> images;
    private LayoutInflater inflater;
    private Context context;
    AnimatorSet front_anim;
    AnimatorSet back_anim;

    public Adapter(List<Images> images, Context context){
        this.images=images;
        this.context=context;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.item,container,false);

        final ImageView imageMain;
        ImageView imageView1, imageView2;
        TextView title, desc;

        imageMain=v.findViewById(R.id.front);
        imageView1=v.findViewById(R.id.imageView);
        imageView2=v.findViewById(R.id.imageView2);
        title=v.findViewById(R.id.title);
        desc=v.findViewById(R.id.description);

        imageMain.setImageResource(images.get(position).getImage());
        imageView1.setImageResource(images.get((position+images.size()-1)%images.size()).getImage());
        imageView2.setImageResource(images.get((position+1)%images.size()).getImage());
        title.setText(images.get(position).getTitle());
        desc.setText(images.get(position).getDesc());

        front_anim= (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.front_animator);
        back_anim= (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.back_animator);

        float scale=context.getResources().getDisplayMetrics().density;

        imageMain.setCameraDistance(8000*scale);
        final RelativeLayout cardBack =v.findViewById(R.id.back);
        cardBack.setCameraDistance(8000*scale);

        Button button=v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(images.get(position).isFront()){
                    front_anim.setTarget(imageMain);

                    back_anim.setTarget(cardBack);
                    front_anim.start();
                    back_anim.start();
                    images.get(position).setFront(false);
                }
                else{
                    front_anim.setTarget(cardBack);
                    back_anim.setTarget(imageMain);
                    back_anim.start();
                    front_anim.start();
                    images.get(position).setFront(true);
                }
            }
        });
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
