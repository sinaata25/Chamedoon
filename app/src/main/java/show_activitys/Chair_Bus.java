package show_activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.Recycler_chair;
import ataie.sina.chamedoon.R;
import model.Model_Chairs;

public class Chair_Bus extends AppCompatActivity {
        String chair;
        List<Model_Chairs>list_chair;
        RecyclerView recyclerView;
        Recycler_chair recycler_chair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chair__bus);
        setup_views();
        setchairs();
    }


    private void setchairs() {
        chair=getIntent().getExtras().getString("chair");
        try {
            JSONArray jsonArray=new JSONArray(chair);
            list_chair=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                Model_Chairs model=new Model_Chairs();
                JSONObject jsonObject=jsonArray.getJSONObject(i);
               model.setSituation_left(jsonObject.getString("situation_left"));
               model.setRezerved_left(jsonObject.getString("rezerved_left"));
               model.setGender_left(jsonObject.getString("gender_left"));

                model.setSituation_mid(jsonObject.getString("situation_mid"));
                model.setRezerved_mid(jsonObject.getString("rezerved_mid"));
                model.setGender_mid(jsonObject.getString("gender_mid"));

                model.setSituation_right(jsonObject.getString("situation_right"));
                model.setRezerved_right(jsonObject.getString("rezerved_right"));
                model.setGender_right(jsonObject.getString("gender_right"));
               list_chair.add(model);
            }
        recyclerView.setAdapter(new Recycler_chair(list_chair,getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void setup_views(){
        recyclerView=findViewById(R.id.recycler_chair);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }





}