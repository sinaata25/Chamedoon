package show_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.Recycler_List_Train_Adapter;
import ataie.sina.chamedoon.R;
import model.Model_ShowList_Train;

public class Train_Show_List extends AppCompatActivity {

    String url="http://192.168.1.106/alibaba/train_list.php";
    String mabda,maghsad,tarikh,tarikh_format_json;
    TextView origin,dist,date,noexist,dirooz,farda;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    Model_ShowList_Train model;
    List<Model_ShowList_Train>list;
    String what_Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train__show__list);
        setup_views();
        get_intent();
        set_text_toolbar();
        get_data();
        handle_rooze_bad();
        handle_rooze_ghabl();

    }

    private void set_text_toolbar() {
        origin.setText(mabda);
        dist.setText(maghsad);
        date.setText(tarikh_format_json);
    }

    private void setup_views() {
        origin=findViewById(R.id.textView_list_mabda);
        dist=findViewById(R.id.textView_list_maghsad);
        date=findViewById(R.id.textView_list_tarikh);
        noexist=findViewById(R.id.textView_noexist_train);
        recyclerView=findViewById(R.id.recycler_train_list);
        dirooz=findViewById(R.id.textView_train_dirooz);
        farda=findViewById(R.id.textView_train_farda);
        constraintLayout=findViewById(R.id.noexist_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));

    }

    private void get_data() {
        list=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        model=new Model_ShowList_Train();
                        model.setId(jsonObject.getString("id"));
                        model.setOrogin(jsonObject.getString("origin"));
                        model.setDistanition(jsonObject.getString("distination"));
                        model.setDate(jsonObject.getString("date"));
                        model.setCapacity(jsonObject.getString("capacity"));
                        model.setCoupe_capacity(jsonObject.getString("coupe_capacity"));
                        model.setType(jsonObject.getString("type"));
                        model.setStart_time(jsonObject.getString("start_time"));
                        model.setEnd_time(jsonObject.getString("end_time"));
                        model.setCompany(jsonObject.getString("company"));
                        model.setPrice(jsonObject.getString("price"));
                        list.add(model);
                   recyclerView.setAdapter(new Recycler_List_Train_Adapter(getApplicationContext(),list));

                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"some prob",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {//send to server
            Map<String, String> params;
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<>();
                params.put("date",tarikh_format_json);
                params.put("distination",maghsad);
                params.put("origin",mabda);
                return params;
            }
        };//send to server

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    private void get_intent(){
      mabda=getIntent().getExtras().getString("origin");
      maghsad=getIntent().getExtras().getString("destination");
      tarikh=getIntent().getExtras().getString("date");
      tarikh_format_json=getIntent().getExtras().getString("date_format");
      what_Type=getIntent().getExtras().getString("what_type");

    }

    public void handle_rooze_bad(){

        farda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split=tarikh_format_json.split("/");
                String day=split[2];
                int int_day=Integer.parseInt(day);
                if(int_day<30){
                    int_day++;
                }
                String day_next=String.valueOf(int_day);
                tarikh_format_json=split[0]+"/"+split[1]+"/"+day_next;
                date.setText(tarikh_format_json);
                get_data();


            }
        });



    }

    public void handle_rooze_ghabl(){

        dirooz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split=tarikh_format_json.split("/");
                String day=split[2];
                int int_day=Integer.parseInt(day);
                if(int_day>1){
                    int_day--;
                }

                String day_next=String.valueOf(int_day);
                tarikh_format_json=split[0]+"/"+split[1]+"/"+day_next;
                date.setText(tarikh_format_json);
                get_data();

            }
        });

    }







}