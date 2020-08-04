package com.example.we_in.camera;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.we_in.R;

public class CameraCardDownFragment extends Fragment { // Test
    LinearLayout carddown1,cardup1;
    ViewGroup viewGroup;
    Animation translateUp = null;
    Animation translateDown = null;

    boolean isPageState = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_camera_card_down1,container,false);  /* -- 이거 fragment_camera_card_down 로 고치기 -- */
        carddown1 = (LinearLayout) viewGroup.findViewById(R.id.card_down);
        cardup1 = (LinearLayout) viewGroup.findViewById(R.id.card_up);

        translateDown = AnimationUtils.loadAnimation(this.getContext(),R.anim.translate_down);
        translateUp = AnimationUtils.loadAnimation(this.getContext(),R.anim.translate_up);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translateDown.setAnimationListener(listener);
        translateUp.setAnimationListener(listener);

        carddown1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("첫번째", isPageState+"");
                cardup1.startAnimation(translateUp);
                carddown1.setVisibility(View.INVISIBLE);
            }
        });
        cardup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("두번째", isPageState+"");
                cardup1.startAnimation(translateDown);
                cardup1.setVisibility(View.INVISIBLE);
            }
        });



        return viewGroup;
    }

    class SlidingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPageState){  // 안보이게 만드는 것
                Log.d("세번째", isPageState+"");
                carddown1.setVisibility(View.VISIBLE);
                isPageState = false;
            }
            else{
                Log.d("네번째", isPageState+"");
                cardup1.setVisibility(View.VISIBLE);
                isPageState = true;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
