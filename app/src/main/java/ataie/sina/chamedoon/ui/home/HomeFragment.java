package ataie.sina.chamedoon.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import ataie.sina.chamedoon.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    CardView cardView_airplane,cardView_train,cardView_bus,cardView_hotel,cardView_tour;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
         root = inflater.inflate(R.layout.fragment_home, container, false);
        setupviews();
        handle_cardviews();
        return root;
    }


    public HomeFragment(){

    }


    public void setupviews()
    {
        cardView_airplane=(CardView)root.findViewById(R.id.cardview_airplane);
        cardView_train=(CardView)root.findViewById(R.id.cardview_train);
        cardView_bus=(CardView)root.findViewById(R.id.cardview_bus);
        cardView_hotel=(CardView)root.findViewById(R.id.cardview_hotel);
        cardView_tour=(CardView)root.findViewById(R.id.cardview_tour);

    }
    public void handle_cardviews(){
        func_card_airplane();
        func_card_train();
        func_card_bus();
        func_card_hotel();
        func_card_tour();
    }

    public void func_card_airplane()
    {
        cardView_airplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.home_to_airplane);
            }
        });
    }


    public void func_card_train()
    {
        cardView_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.home_to_train);
            }
        });
    }


    public void func_card_bus()
    {
        cardView_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.home_to_bus);
            }
        });
    }


    public void func_card_hotel()
    {
        cardView_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.home_to_hotel);
            }
        });
    }


    public void func_card_tour()
    {
        cardView_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.home_to_tour);
            }
        });
    }








}