package pick_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.Recycler_Date_Adapter;
import adapters.Recycler_month_Adapter;
import ataie.sina.chamedoon.R;
import model.Model_Date_Pick;

public class DATE_Picker extends AppCompatActivity {
        RecyclerView recyclerView,recyclerview_month;
        List<Model_Date_Pick>list;  List<Model_Date_Pick>list_month;
        TextView txt_day,txt_month;
        String month_getted;
        String month_format_num;
        Button btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        setup_views();
        setdata();
        setmonth_data();
        button_handle();
    }

    private void button_handle() {
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setdata()
    {
            Model_Date_Pick model;
            for(int i=1;i<31;i++){
            model=new Model_Date_Pick();
            model.setDay(String.valueOf(i));
            list.add(model);
        }
        recyclerView.setAdapter(new Recycler_Date_Adapter(list, getApplicationContext(), new Recycler_Date_Adapter.OnDatePicked() {
            @Override
            public void Onday(String day) {
                if(month_getted!=null){
                Intent intent=new Intent();
                case_month_format();
                intent.putExtra("date",day);
                intent.putExtra("month",month_getted);
                intent.putExtra("month_format",month_format_num);
                setResult(RESULT_OK,intent);
                txt_day.setText(day);}else{
                    Toast.makeText(getApplicationContext(),"اول ماهو انتخاب کن!",Toast.LENGTH_LONG).show();
                }

            }
        }));
    }

    public void setmonth_data(){

        Model_Date_Pick model1=new Model_Date_Pick();
        model1.setMonth("فروردین");
        list_month.add(model1);

        Model_Date_Pick model2=new Model_Date_Pick();
        model2.setMonth("اردیبهشت");
        list_month.add(model2);

        Model_Date_Pick model3=new Model_Date_Pick();
        model3.setMonth("خرداد");
        list_month.add(model3);

        Model_Date_Pick model4=new Model_Date_Pick();
        model4.setMonth("تیر");
        list_month.add(model4);

        Model_Date_Pick model5=new Model_Date_Pick();
        model5.setMonth("مرداد");
        list_month.add(model5);

        Model_Date_Pick model6=new Model_Date_Pick();
        model6.setMonth("شهریور");
        list_month.add(model6);

        Model_Date_Pick model7=new Model_Date_Pick();
        model7.setMonth("مهر");
        list_month.add(model7);

        Model_Date_Pick model8=new Model_Date_Pick();
        model8.setMonth("آبان");
        list_month.add(model8);


        Model_Date_Pick model9=new Model_Date_Pick();
        model9.setMonth("آذر");
        list_month.add(model9);



        Model_Date_Pick model10=new Model_Date_Pick();
        model10.setMonth("دی");
        list_month.add(model10);



        Model_Date_Pick model11=new Model_Date_Pick();
        model11.setMonth("بهمن");
        list_month.add(model11);



        Model_Date_Pick model12=new Model_Date_Pick();
        model12.setMonth("اسفند");
        list_month.add(model12);

    recyclerview_month.setAdapter(new Recycler_month_Adapter(list_month, getApplicationContext(), new Recycler_month_Adapter.OnMonth() {
        @Override
        public void Onselected(String month) {
            txt_month.setText(month);
            month_getted=month;
        }
    }));

    }



    public void setup_views(){
        recyclerView=findViewById(R.id.recyclerview_day);
        recyclerview_month=findViewById(R.id.recyclerview_month);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerview_month.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        list=new ArrayList<>();
        list_month=new ArrayList<>();
        txt_day=findViewById(R.id.rooz_txt);
        txt_month=findViewById(R.id.mah_txt);
        btn_end=findViewById(R.id.btn_end);

    }


    public void case_month_format(){

        switch (month_getted){
            case "فروردین":month_format_num="1";break;
            case "اردیبهشت":month_format_num="2";break;
            case "خرداد":month_format_num="3";break;
            case "تیر":month_format_num="4";break;
            case "مرداد":month_format_num="5";break;
            case "شهریور":month_format_num="6";break;
            case "مهر":month_format_num="7";break;
            case "آبان":month_format_num="8";break;
            case "آذر":month_format_num="9";break;
            case "دی":month_format_num="10";break;
            case "بهمن":month_format_num="11";break;
            case "اسفند":month_format_num="12";break;
        }

    }


}