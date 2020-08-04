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
    LinearLayout carddown1,cardopen;
    ViewGroup viewGroup;
    Animation translateUp = null;
    Animation translateDown = null;
    Animation CardUp = null;
    Animation CardDown = null;

    boolean[] isPageState = new boolean[2];
    int num;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_camera_card_down1,container,false);  /* -- 이거 fragment_camera_card_down 로 고치기 -- */
        carddown1 = (LinearLayout) viewGroup.findViewById(R.id.card_down);
        cardopen = (LinearLayout) viewGroup.findViewById(R.id.first_open);

        translateDown = AnimationUtils.loadAnimation(this.getContext(),R.anim.translate_down);
        translateUp = AnimationUtils.loadAnimation(this.getContext(),R.anim.translate_up);
        CardUp = AnimationUtils.loadAnimation(this.getContext(),R.anim.card_up);
        CardDown = AnimationUtils.loadAnimation(this.getContext(),R.anim.card_down);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translateDown.setAnimationListener(listener);
        translateUp.setAnimationListener(listener);
        CardUp.setAnimationListener(listener);
        CardDown.setAnimationListener(listener);
        isPageState[0] = false;
        isPageState[1] = false;
        num=1;

        /*carddown1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("두번째", isPageState[0]+"//"+isPageState[1]);
                carddown1.startAnimation(translateUp);
                Log.d("이걸 걸치고 가나", isPageState[0]+"//"+isPageState[1]);
                cardopen.startAnimation(CardUp);
            }
        });
        cardopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("첫번째", isPageState[0]+"//"+isPageState[1]);
                carddown1.startAnimation(translateDown);
                cardopen.startAnimation(CardDown);
            }
        });*/
        carddown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageState[0]&&isPageState[1]){
                    Log.d("첫번째", isPageState[0]+"//"+isPageState[1]);
                    carddown1.startAnimation(translateDown);
                    cardopen.startAnimation(CardDown);
                }
                else{
                    Log.d("두번째", isPageState[0]+"//"+isPageState[1]);
                    carddown1.startAnimation(translateUp);
                    Log.d("이걸 걸치고 가나", isPageState[0]+"//"+isPageState[1]);
                    cardopen.startAnimation(CardUp);
                }
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
            if (isPageState[1]){  // 안보이게 만드는 것
                Log.d("세번째", isPageState[0]+"//"+isPageState[1]+""+num);
                //carddown1.setVisibility(View.VISIBLE);
                if (num==1){
                    isPageState[0]=false;
                    num++;
                }
                else {
                    isPageState[1]=false;
                    num=1;
                }

            }
            else{
                Log.d("네번째", isPageState[0]+"//"+isPageState[1]+""+num);
                //cardup1.setVisibility(View.VISIBLE);
                if (num==1){
                    isPageState[0]=true;
                    num++;
                }
                else{
                    isPageState[1]=true;
                    num=1;
                }


            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
