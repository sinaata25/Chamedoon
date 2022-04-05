package pick_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.Recycler_City_Adapter;
import ataie.sina.chamedoon.R;
import fragments.Fragment_Train;
import model.Model_Train_City;


public class Train_Pick_city extends AppCompatActivity {
    private static final String  url_city ="http://192.168.1.106/alibaba/city.php";
    Model_Train_City model;
    List<Model_Train_City>list;     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train__pick_city);
        setup();
        getdata();
    }


    private void getdata()
    {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url_city, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            try {
                    for(int i=0;i<response.length();i++)
                    {   model=new Model_Train_City();
                        JSONObject jsonObject=response.getJSONObject(i);
                        model.setCity(jsonObject.getString("city"));
                        model.setId(jsonObject.getString("id"));
                        list.add(model);
                    }

            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }

                recyclerView.setAdapter(new Recycler_City_Adapter(list, getApplicationContext(), new Recycler_City_Adapter.Oncitypick() {
                    @Override
                    public void pick(String city) {
                        Intent intent=new Intent();
                        intent.putExtra("mycity",city);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Connection Eror",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
    public void setup()
    {
        list=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_city_train);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }





}