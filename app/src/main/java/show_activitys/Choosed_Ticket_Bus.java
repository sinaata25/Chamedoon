package show_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ataie.sina.chamedoon.R;
import model.Model_Showlist_Bus;

public class Choosed_Ticket_Bus extends AppCompatActivity {
    Model_Showlist_Bus model_showlist_bus;
    Button btn_chair;
    int time_end;
    TextView textview_type,textview_price,textview_time,textview_date,textview_header_origin,textview_header_dist;
    TextView textview_time_go,textview_time_end,textview_origin,textview_dist,textView_company,textview_distance;
    String chairs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosed__ticket__bus);
        model_showlist_bus=getIntent().getParcelableExtra("model_bus");
        setupviews();
        settexts();
        handle_btn_chair();
    }




    private void setupviews() {
        textview_header_origin=findViewById(R.id.bus_textView_picked_mabda);
        textview_header_dist=findViewById(R.id.bus_textView_picked_maghsad);
        textview_date=findViewById(R.id.bus_textView_piked_tarikh);
        textview_origin=findViewById(R.id.bus_textView_picked_origin);
        textview_dist=findViewById(R.id.bus_textView_picked_dist);
        textview_time_go=findViewById(R.id.bus_textView_picked_time_start);
        textview_time_end=findViewById(R.id.bus_textView_picked_time_end);
        textView_company=findViewById(R.id.bus_textView_company_pick);
        textview_time=findViewById(R.id.bus_time_go);
        textview_type=findViewById(R.id.bus_type_go);
        textview_distance=findViewById(R.id.bus_distance);
        textview_price=findViewById(R.id.bus_price_go);
        btn_chair=findViewById(R.id.bus_btn_choose_chair);
    }

    private String end_time_cal(){
        String distance=model_showlist_bus.getDistance();
        String start_time=model_showlist_bus.getTime();
        int int_distance=Integer.parseInt(distance);
        int int_starttime=Integer.parseInt(start_time);
        time_end=int_starttime+int_distance/80;
        if(time_end>24){
            time_end=time_end%24;
        }
        return String.valueOf(time_end);
    }

    private void settexts() {
    textview_time_go.setText(model_showlist_bus.getTime());
    textview_time_end.setText(end_time_cal());
    textview_header_origin.setText(model_showlist_bus.getOrigin());
    textview_header_dist.setText(model_showlist_bus.getDistination());
    textview_date.setText(model_showlist_bus.getDate());
    textview_origin.setText(model_showlist_bus.getOrigin_terminal());
    textview_dist.setText(model_showlist_bus.getDestination_terminal());
    textView_company.setText(model_showlist_bus.getCompany());
    textview_time.setText(model_showlist_bus.getTime());
    textview_type.setText(model_showlist_bus.getType());
    textview_distance.setText(model_showlist_bus.getDistance()+" "+"کیلومتر");
    textview_price.setText(model_showlist_bus.getPrice()+" "+"ریال");
    chairs=model_showlist_bus.getChairs();
    }

    private void handle_btn_chair() {
btn_chair.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),Chair_Bus.class);
        intent.putExtra("chair",chairs);
        startActivity(intent);
    }});
    }



}