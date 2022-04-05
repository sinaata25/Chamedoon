package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.chamedoon.R;
import pick_activitys.Train_Pick_city;

public class Fragment_Airplane extends Fragment {
    View view;
    TextView origin,distnation;
    ImageView baraks;  int star_pick=0; int end_pick=0;
    private static final int REQUEST_CODE_AIR_PICK = 1003;
    private static final int REQUEST_CODE_DEST_PICK = 1004;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.airplane_fragment,container,false);
         setup();
         pick_city();
         pick_city_Dests();
         baraks_btn();
        return view;
    }

    public void setup(){
        origin=view.findViewById(R.id.textView_mabda_airplane);
        distnation=view.findViewById(R.id.textView_maghsad_airplane);
        baraks=view.findViewById(R.id.img_baraks_airplane);
    }

    public void pick_city(){
        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Train_Pick_city.class);
                startActivityForResult(intent,REQUEST_CODE_AIR_PICK);
            }
        });


    }

    public void pick_city_Dests(){
        distnation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Train_Pick_city.class);
                startActivityForResult(intent,REQUEST_CODE_DEST_PICK);
            }
        });


    }
    public void baraks_btn(){

       baraks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(star_pick==1 && end_pick==1){
               String start; String end;
               start=origin.getText().toString();
               end=distnation.getText().toString();
               origin.setText(end);
               distnation.setText(start);}
           }
       });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_AIR_PICK && resultCode==-1 && data!=null){
            origin.setText(data.getExtras().getString("mycity"));
            star_pick=1;
        }
        else if(requestCode==REQUEST_CODE_DEST_PICK && resultCode==-1 && data!=null){
            distnation.setText(data.getExtras().getString("mycity"));
            end_pick=1;
        }
    }
}
