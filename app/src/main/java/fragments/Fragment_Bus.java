package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.chamedoon.R;
import pick_activitys.DATE_Picker;
import pick_activitys.Train_Pick_city;
import show_activitys.Bus_Show_List;
import show_activitys.Train_Show_List;

public class Fragment_Bus extends Fragment {
    View view;
    TextView textView_origin,textView_dist,textView_date,textView_num;
    ImageView mosbat,manfi,baraks;
    Button search;
    String tarikh_format;
    int picked_start=0,picked_end=0,picked_date=0;
    int number_passenger=1;
    private static final int REQUEST_CODE_CITY = 1005;
    private static final int REQUEST_CODE_CITY_MAGHSAD = 1006;
    private static final int REQUEST_CODE_DATE = 1007;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.bus_fragment,container,false);
        setup_views();
        handle_mabada();
        handle_maghsad();
        handle_date();
        numpass();
        handle_search_btn();
        return view;
    }


    private void setup_views(){
        textView_origin=view.findViewById(R.id.textView_mabda_bus);
        textView_dist=view.findViewById(R.id.textView_maghsad_bus);
        textView_date=view.findViewById(R.id.textView_date_bus);
        textView_num=view.findViewById(R.id.textView_Passengers_bus);
        mosbat=view.findViewById(R.id.mosbat_id_bus);
        manfi=view.findViewById(R.id.manfi_id_bus);
        baraks=view.findViewById(R.id.imageView_baraks_bus);
        search=view.findViewById(R.id.button_search_bus);

    }

    private void numpass(){
        mosbat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number_passenger<30){
                    number_passenger+=1;
                    textView_num.setText(String.valueOf(number_passenger));
                }
            }
        });


        manfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number_passenger>1){
                    number_passenger-=1;
                    textView_num.setText(String.valueOf(number_passenger));
                }
            }
        });



    }

    public void handle_mabada(){
        textView_origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Train_Pick_city.class);
                startActivityForResult(intent,REQUEST_CODE_CITY);
            }
        });

    }

    public void handle_maghsad(){
    textView_dist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(),Train_Pick_city.class);
            startActivityForResult(intent,REQUEST_CODE_CITY_MAGHSAD);
        }
    });
    }


    public void handle_date(){
        textView_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DATE_Picker.class);
                startActivityForResult(intent,REQUEST_CODE_DATE);
            }
        });
    }

    private void handle_search_btn(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picked_start==1 && picked_end==1 && picked_date==1){

                    Intent intent=new Intent(getContext(), Bus_Show_List.class);
                    intent.putExtra("origin",textView_origin.getText().toString());
                    intent.putExtra("dist",textView_dist.getText().toString());
                    intent.putExtra("date",tarikh_format);
                    intent.putExtra("num",String.valueOf(number_passenger));
                    startActivity(intent);

                }



            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE_CITY && resultCode==-1 &&data!=null){
            String city=data.getExtras().getString("mycity");
            textView_origin.setText(city);
            picked_start=1;
        }

      else  if (requestCode==REQUEST_CODE_CITY_MAGHSAD && resultCode==-1 &&data!=null){
            String city=data.getExtras().getString("mycity");
            textView_dist.setText(city);
            picked_end=1;
        }

        else  if (requestCode==REQUEST_CODE_DATE && resultCode==-1 &&data!=null){
            String day=data.getExtras().getString("date");
            String month=data.getExtras().getString("month");
            String month_format=data.getExtras().getString("month_format");
            String format_month=data.getExtras().getString("month_format");
            textView_date.setText(day+"/"+month+"/"+"1399");
            tarikh_format="1399"+"/"+month_format+"/"+day;
            picked_date=1;

        }


    }
}
