package show_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import ataie.sina.chamedoon.R;

public class Edit_Activity extends AppCompatActivity {
    EditText edt_name,edt_family,edt_number,edt_email;
    TextView edt_sex;
    Button btn_ok;
    private static final String url_edit_profile ="http://192.168.1.106/alibaba/edit_profile.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        setup_views();
        set_defults();
        handle_sex();
        send_data_server();
    }

    private void set_defults(){
        SharedPreferences sharedPreferences=getSharedPreferences("log",MODE_PRIVATE);
        edt_name.setText(sharedPreferences.getString("name",""));
        edt_family.setText(sharedPreferences.getString("family",""));
        edt_email.setText(sharedPreferences.getString("password",""));
        edt_number.setText(sharedPreferences.getString("number",""));
        edt_sex.setText(sharedPreferences.getString("sex",""));

    }
    public void setup_views(){
        edt_name=findViewById(R.id.editactivity_name);
        edt_family=findViewById(R.id.editactivity_family);
        edt_number=findViewById(R.id.editactivity_number);
        edt_email=findViewById(R.id.editactivity_email);
        edt_sex=findViewById(R.id.editactivity_sex);
        btn_ok=findViewById(R.id.editactivity_button_ok);
    }
    private void handle_sex(){
            edt_sex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     GenderPickerDialog genderPickerDialo=new GenderPickerDialog(Edit_Activity.this);
                    genderPickerDialo.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                        @Override
                        public void onSelectingGender(String value) {
                            edt_sex.setText(value);
                        }
                    });
                  genderPickerDialo.show();
                }

            });
    }
    private void send_data_server(){
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     /////////////////////////////////////////
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url_edit_profile, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"ویرایش انجام شد" ,Toast.LENGTH_SHORT).show();
                       SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("log",getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("name",edt_name.getText().toString());
                        editor.putString("family",edt_family.getText().toString());
                        editor.putString("password",edt_email.getText().toString());
                        editor.putString("sex",edt_sex.getText().toString());
                        editor.putString("number",edt_number.getText().toString());
                        editor.apply();
                        Intent intent=new Intent();
                        intent.putExtra("name",edt_name.getText().toString());
                        intent.putExtra("family",edt_family.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"ویرایش انجام نشد!" ,Toast.LENGTH_SHORT).show();
                    }
                }){//send to server

                    Map<String,String>params;
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        params=new HashMap<>();
                        SharedPreferences sharedPreferences=getSharedPreferences("log",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        params.put("email",sharedPreferences.getString("email",""));
                        params.put("nme",edt_name.getText().toString());
                        params.put("family",edt_family.getText().toString());
                        params.put("password",edt_email.getText().toString());
                        params.put("nmbr",edt_number.getText().toString());
                        params.put("gender",edt_sex.getText().toString());

                        return params;
                    }
                };//send to server

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
     /////////////////////////////////////////
            }
        });
    }





}