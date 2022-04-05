package show_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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

import adapters.Recycler_list_Bus_Adapter;
import ataie.sina.chamedoon.R;
import model.Model_Chairs;
import model.Model_Showlist_Bus;

public class Bus_Show_List extends AppCompatActivity {
String origin,distination,date,num_pass;
TextView rooz_bad,rooz_ghabl,tarikh,dest,mabda;
RecyclerView recyclerView;
List<Model_Showlist_Bus>list;
List<Model_Chairs>list_chairs;
    private static final String url_bus ="http://192.168.1.106/alibaba/bus_list.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus__show__list);
        setup_views();
        get_intent();
        set_toolbar_text();
        get_data();
        next_day();
        before_day();
    }

    private void before_day() {
        rooz_ghabl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split=date.split("/");
                String day=split[2];
                int day_int=Integer.parseInt(day);
                if(day_int>1){
                    day_int--;
                }
                String day_befored=String.valueOf(day_int);
                date=split[0]+"/"+split[1]+"/"+day_befored;
                tarikh.setText(split[0]+"/"+split[1]+"/"+day_befored);
                get_data();
            }
        });
    }

    private void next_day() {
        rooz_bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] split=date.split("/");
                String day=split[2];
                int day_int=Integer.parseInt(day);
                if(day_int<30){
                    day_int++;
                }
                String day_befored=String.valueOf(day_int);
                date=split[0]+"/"+split[1]+"/"+day_befored;
                tarikh.setText(split[0]+"/"+split[1]+"/"+day_befored);
                get_data();
            }
        });



    }

    public Bus_Show_List(){
    }

    private void get_intent(){
       origin=getIntent().getExtras().getString("origin");
       distination=getIntent().getExtras().getString("dist");
       date=getIntent().getExtras().getString("date");
       num_pass=getIntent().getExtras().getString("num");
    }

    private void setup_views(){
        mabda=findViewById(R.id.textView_list_mabda_bus);
        dest=findViewById(R.id.textView_list_maghsad_bus);
        tarikh=findViewById(R.id.textView_list_tarikh_bus);
        rooz_bad=findViewById(R.id.textView_train_farda_bus);
        rooz_ghabl=findViewById(R.id.textView_train_dirooz_bus);
        recyclerView=findViewById(R.id.recycle_bus_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));

    }

    private void set_toolbar_text(){
        mabda.setText(origin);
        dest.setText(distination);
        tarikh.setText(date);
    }

    private void get_data(){
        list=new ArrayList<>();
        list_chairs=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_bus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<response.length();i++){
                        Model_Showlist_Bus model=new Model_Showlist_Bus();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        model.setId(jsonObject.getString("id"));
                        model.setOrigin(jsonObject.getString("origin"));
                        model.setDistination(jsonObject.getString("distination"));
                        model.setPrice(jsonObject.getString("price"));
                        model.setDate(jsonObject.getString("date"));
                        model.setType(jsonObject.getString("type"));
                        model.setCapacity(jsonObject.getString("capacity"));
                        model.setCompany(jsonObject.getString("company"));
                        model.setTime(jsonObject.getString("time"));
                        model.setOrigin_terminal(jsonObject.getString("origin_terminal"));
                        model.setDestination_terminal(jsonObject.getString("destination_terminal"));
                        model.setDistance(jsonObject.getString("distance"));
                        ///chairs
                        String chair=jsonObject.getString("chairs");
                        model.setChairs(chair);
                        ///chairs
                        list.add(model);
                        recyclerView.setAdapter(new Recycler_list_Bus_Adapter(getApplicationContext(),list));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
            }
        }){//send to server
            Map<String,String>param;
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                param=new HashMap<>();
                param.put("origin",origin);
                param.put("distination",distination);
                param.put("date",date);
                return param;
            }
        };//send to server

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }









}