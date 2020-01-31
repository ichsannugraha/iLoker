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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.SlideAnimationValue;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    //SliderView sliderView;
    private SliderLayout sliderLayout;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Loker> list;
    MyAdapter adapter;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = rootView.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(2);
        setSliderViews();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        list = new ArrayList<Loker>();

        reference = FirebaseDatabase.getInstance().getReference().child("loker");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Loker l = dataSnapshot1.getValue(Loker.class);
                    list.add(l);
                }
                adapter = new MyAdapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
        return rootView;
    }

    private void search(String str){
        ArrayList<Loker> lokerList = new ArrayList<>();
        for (Loker object : list) {
            if (object.getNamaLoker().toLowerCase().contains(str.toLowerCase())) {
                lokerList.add(object);
            }
        }
        MyAdapter myAdapter = new MyAdapter(getContext(), lokerList);
        recyclerView.setAdapter(myAdapter);
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
