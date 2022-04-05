package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ataie.sina.chamedoon.MainActivity;
import ataie.sina.chamedoon.R;
import ataie.sina.chamedoon.ui.dashboard.DashboardFragment;

public class Vorood_Fragment_Dialog extends DialogFragment {
    EditText edittext_email_phone,edittext_pass;
    Button btn_logup,btn_login;
    public Callback callback;
    private static final String url ="http://192.168.1.106/alibaba/get_user_login.php";
    View view;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        view= LayoutInflater.from(getContext()).inflate(R.layout.vorood_fragment_dialog,null,false);
        setup_views();
        handle_btn_logup();
        handle_btn_login();
        builder.setView(view);
        return builder.create();
    }

    private void setup_views(){
        btn_login=view.findViewById(R.id.btn_login_vorood);
        btn_logup=view.findViewById(R.id.btn_logup_vorood);
        edittext_email_phone=view.findViewById(R.id.edittext_email_phone_vorrod);
        edittext_pass=view.findViewById(R.id.edittext_pass_vorrod);
    }
    private void handle_btn_logup(){

        btn_logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null){
                    callback.Onregisterclicked();
                    dismiss();
                }

            }
        });

    }
    private void handle_btn_login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edittext_email_phone.getText().toString().isEmpty() && !edittext_pass.getText().toString().isEmpty()){
                    check_is_there();
                }
            }
        });
    }

    private void check_is_there(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("no")){
                    Toast.makeText(getContext(),"ایمیل یا پسوورد اشتباه است",Toast.LENGTH_SHORT).show();
                }else {
                    String[] split=response.split("/");
                    SharedPreferences sharedPreferences=getContext().getSharedPreferences("log",getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",split[0]);
                    editor.putString("family",split[1]);
                    editor.putString("email",split[3]);
                    editor.putString("number",split[4]);
                    editor.putString("sex",split[2]);
                    editor.putString("money",split[5]);
                    editor.putString("password",split[6]);
                    editor.putBoolean("loged_ok",true);
                    editor.apply();
                    dismiss();
                    if(callback!=null){
                        callback.Onloginclicked(split[0],split[1]);
                    }
                    Toast.makeText(getContext(),"ورود با موفقیت انجام شد",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"خطا در اتصال به سرور",Toast.LENGTH_SHORT).show();
            }
        }){//send to server
                Map<String,String>params;

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params=new HashMap<>();
                params.put("email",edittext_email_phone.getText().toString());
                params.put("password",edittext_pass.getText().toString());
                return params;
            }
        };//send to server

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    public interface Callback{
        void  Onloginclicked(String name,String family);
        void Onregisterclicked();
    }


}
