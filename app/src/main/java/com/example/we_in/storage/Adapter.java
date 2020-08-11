package com.example.we_in.storage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.we_in.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewPagerViewHolder>{
    private Context context;
    private List<Images> images;

    AnimatorSet front_anim, back_anim;

    public Adapter(List<Images> images, Context context){
        this.images = images;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.setImage(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title, desc;
        private CardView cardFront, cardBack;
        private RelativeLayout back_contents;

        ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.front);

            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.description);

            front_anim= (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.front_animator);
            back_anim= (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.back_animator);

            float scale=context.getResources().getDisplayMetrics().density;

            cardFront =itemView.findViewById(R.id.cardMainFront);
            cardFront.setCameraDistance(8000*scale);
            cardBack =itemView.findViewById(R.id.cardMainBack);
            cardBack.setCameraDistance(8000*scale);

            back_contents=itemView.findViewById(R.id.back);
        }

        void setImage(final Images image){
            imageView.setImageResource(image.getImage());
            title.setText(image.getTitle());
            desc.setText(image.getDesc());

            /* Hide back_contents(contents in cardBack) after animation. */
            final Animator.AnimatorListener visibility_AL=new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(image.isFront())
                        back_contents.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };

            /* Card Flip */
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(image.isFront()){
                        front_anim.setTarget(cardFront);
                        back_anim.setTarget(cardBack);
                        front_anim.start();
                        back_anim.start();
                        image.setFront(false);

                        back_contents.setVisibility(itemView.VISIBLE);
                    }
                    else{
                        front_anim.setTarget(cardBack);
                        back_anim.setTarget(cardFront);

                        front_anim.addListener(visibility_AL);

                        back_anim.start();
                        front_anim.start();
                        image.setFront(true);
                    }
                }
            });
        }
    }
}