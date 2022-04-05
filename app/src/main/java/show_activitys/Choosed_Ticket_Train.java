package show_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ataie.sina.chamedoon.R;
import model.Model_ShowList_Train;

public class Choosed_Ticket_Train extends AppCompatActivity {
    Model_ShowList_Train model;
    TextView textview_type,textview_price,textview_time,textview_date,textview_header_origin,textview_header_dist;
    TextView textview_time_go,textview_time_end,textview_origin,textview_dist,textView_company;
    Button btn_choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosed__ticket__train);
        getparcelable();
        setup_views();
        set_texts();
    }

    private void getparcelable() {
        model=getIntent().getParcelableExtra("Model_ShowList_Train");
    }

    private void setup_views(){
        textview_price=findViewById(R.id.price_go_train);
        textview_time=findViewById(R.id.time_go_train);
        textview_type=findViewById(R.id.type_go_train);
        btn_choose=findViewById(R.id.btn_choose_this_train);
        textview_date=findViewById(R.id.textView_piked_tarikh);
        textview_header_origin=findViewById(R.id.textView_picked_mabda);
        textview_header_dist=findViewById(R.id.textView_picked_maghsad);
        textview_time_go=findViewById(R.id.textView_picked_time_train_start);
        textview_time_end=findViewById(R.id.textView_picked_time_train_end);
        textview_origin=findViewById(R.id.textView_picked_origin_train);
        textview_dist=findViewById(R.id.textView_picked_dist_train);
        textView_company=findViewById(R.id.textView_company_pick);
    }


    private void set_texts(){
        textview_price.setText(model.getPrice()+"ریال");
        textview_time.setText(model.getStart_time());
        textview_type.setText(model.getType());
        textview_date.setText(model.getDate());
        textview_header_origin.setText(model.getOrogin());
        textview_header_dist.setText(model.getDistanition());
        textview_time_go.setText(model.getStart_time());
        textview_time_end.setText(model.getEnd_time());
        textview_origin.setText(model.getOrogin());
        textview_dist.setText(model.getDistanition());
        textView_company.setText(model.getCompany());

    }

}