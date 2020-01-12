package com.example.iloker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.SlideAnimationValue;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {
    //SliderView sliderView;
    private SliderLayout sliderLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = rootView.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(2);

        setSliderViews();
        return rootView;
    }

    private void setSliderViews(){
        for (int i = 0; i <= 4; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.slider1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.slider2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.slider3);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.slider4);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.slider5);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    //Toast.makeText(HomeFragment.this, "This is a slider" + (finalI + 1);
                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }
}
