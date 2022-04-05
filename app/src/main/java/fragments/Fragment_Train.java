package fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.chamedoon.R;
import ataie.sina.chamedoon.Splash_Activity;
import ataie.sina.chamedoon.ui.home.HomeFragment;
import model.Model_data;
import pick_activitys.DATE_Picker;
import pick_activitys.Train_Pick_city;
import show_activitys.Train_Show_List;

public class Fragment_Train extends Fragment{
    TextView txt_mabda,txt_maghsad,date_go,passengers;
    ImageView imageView_baraks,mosbat,manfi;
    String txt_day,txt_month,month_format,tarikh_format;
    String Train="Train";
    Button search;
    int num_pass=1;
    View view; int Picked_start=0; int Picked_end=0,date_picked=0;
    private static final int RESULT_CODE_PICK_CITY = 1001;
    private static final int RESULT_CODE_PICK_distnation= 1002;
    private static final int RESULT_CODE_PICK_date= 1003;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.train_fragment,container,false);
        setupview();
        pick_mabda();
        pick_maghsad();
        Image_Baraks();
        date_handle();
        num_passenger();
        button_search();

        return view;
    }



    private void button_search() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Picked_start==1 && Picked_end==1 && date_picked==1){
                        Intent intent=new Intent(getContext(), Train_Show_List.class);
                        intent.putExtra("origin",txt_mabda.getText().toString());
                        intent.putExtra("destination",txt_maghsad.getText().toString());
                        intent.putExtra("date",date_go.getText().toString());
                        intent.putExtra("num",String.valueOf(num_pass));
                        intent.putExtra("date_format",tarikh_format);
                        intent.putExtra("what_type",Train);
                        startActivity(intent);

                }else{
                    Toast.makeText(getContext(),"همه فیلدارو پر کن!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void num_passenger() {

    mosbat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(num_pass<30){
            num_pass+=1;
            passengers.setText(String.valueOf(num_pass));
        }}
    });


    manfi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(num_pass>1){
            num_pass-=1;
            passengers.setText(String.valueOf(num_pass));
        }}
    });

    }


    public void setupview(){
        txt_mabda=(TextView)view.findViewById(R.id.textView_mabda_train);
        txt_maghsad=(TextView)view.findViewById(R.id.textView_maghsad_train);
        imageView_baraks=(ImageView)view.findViewById(R.id.imageView_baraks);
        date_go=(TextView)view.findViewById(R.id.textView_date_go_train);
        passengers=(TextView)view.findViewById(R.id.textView_Passengers);
        mosbat=(ImageView)view.findViewById(R.id.mosbat_id);
        manfi=(ImageView)view.findViewById(R.id.manfi_id);
        search=(Button)view.findViewById(R.id.button_search_for_train);
    }

    public void pick_mabda()
    {
        txt_mabda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Train_Pick_city.class);
                startActivityForResult(intent,RESULT_CODE_PICK_CITY);

            }
        });
    }

    public void pick_maghsad(){
        txt_maghsad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Train_Pick_city.class);
                startActivityForResult(intent,RESULT_CODE_PICK_distnation);
            }
        });
    }

    public void Image_Baraks(){
        imageView_baraks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_start;
                String txt_end;
                if(Picked_start==1 && Picked_end==1){
                   txt_start=txt_mabda.getText().toString();
                   txt_end=txt_maghsad.getText().toString();
                   txt_mabda.setText(txt_end);
                   txt_maghsad.setText(txt_start);
                }
            }
        });
    }

    public void date_handle(){
        date_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DATE_Picker.class);
                startActivityForResult(intent,RESULT_CODE_PICK_date);

            }
        });


}




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==RESULT_CODE_PICK_CITY && resultCode==-1 && data!=null){
            txt_mabda.setText(data.getExtras().getString("mycity"));
            Picked_start=1;
        }
        else if(requestCode==RESULT_CODE_PICK_distnation && resultCode==-1 && data!=null){
            txt_maghsad.setText(data.getExtras().getString("mycity"));
            Picked_end=1;
        }
        else if(requestCode==RESULT_CODE_PICK_date && resultCode==-1 && data!=null){
            txt_day=data.getExtras().getString("date");
            txt_month=data.getExtras().getString("month");
            month_format=data.getExtras().getString("month_format");
            date_go.setText(txt_day+"/"+txt_month+"/"+"1399");
            tarikh_format="1399"+"/"+month_format+"/"+txt_day;
            date_picked=1;
        }
    }





    public Fragment_Train(){

    }







}
