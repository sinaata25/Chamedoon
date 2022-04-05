package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

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
import ataie.sina.chamedoon.MainActivity;
import ataie.sina.chamedoon.R;

public class Logup_Fragment_Dialog extends DialogFragment {

    TextView gender;
    EditText edt_name,edt_family,edt_email,edt_number,edt_password;
    Button btn_logup;
    View view;
    public   Callback_Logup callback_logup;
    String sex;
    SharedPreferences sharedPreferences;
    private static final String url_check_email ="http://192.168.1.106/alibaba/check_email.php";
    private static final String url_insert ="http://192.168.1.106/alibaba/insert_user.php";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        view= LayoutInflater.from(getContext()).inflate(R.layout.logup_fragment_dialog,null,false);
        setup_views();
        gender_handle();
        handle_btn_logup();
        builder.setView(view);
        return builder.create();
    }

    private void setup_views(){
        gender=view.findViewById(R.id.gender);
        edt_name=view.findViewById(R.id.edittext_name_logup);
        edt_family=view.findViewById(R.id.edittext_family_logup);
        edt_email=view.findViewById(R.id.edittext_email_logup);
        edt_number=view.findViewById(R.id.edittext_number_logup);
        edt_password=view.findViewById(R.id.edittext_password_logup);
        btn_logup=view.findViewById(R.id.btn_logup_logup);
    }
    private void gender_handle() {
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenderPickerDialog genderPickerDialog=new GenderPickerDialog(getContext());
                genderPickerDialog.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                    @Override
                    public void onSelectingGender(String value) {
                        sex=value;
                        gender.setText(value);
                    }
                });
                genderPickerDialog.show();
            }
        });
    }
    private void handle_btn_logup(){
        btn_logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_information();
            }
        });
    }
    private void check_information() {
        int all_filed = 0;
        if (edt_name.getText().toString().isEmpty() || edt_family.getText().toString().isEmpty() || edt_email.getText().toString().isEmpty()
                || edt_number.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty() || sex == null) {
            Toast.makeText(getContext(), "همه فیلد هارو پر کن!", Toast.LENGTH_SHORT).show();
        } else {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_check_email, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("yes")) {
                        Toast.makeText(getContext(), "این ایمیل یا شماره قبلا ثبت نام شده است!", Toast.LENGTH_SHORT).show();
                    } else {
                        insert_to_table();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {//send to server

                Map<String, String> params;

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    params = new HashMap<>();
                    params.put("email", edt_email.getText().toString());
                    return params;
                }
            };//send to server

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);


        }

    }
    private void insert_to_table(){//
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(callback_logup!=null){
                    callback_logup.onRegistered(edt_name.getText().toString(),edt_family.getText().toString());
                }
                Toast.makeText(getContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                sharedPreferences=getContext().getSharedPreferences("log", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",edt_name.getText().toString());
                editor.putString("family",edt_family.getText().toString());
                editor.putString("email",edt_email.getText().toString());
                editor.putString("number",edt_number.getText().toString());
                editor.putString("password",edt_password.getText().toString());
                editor.putString("sex",sex);
                editor.putBoolean("loged_ok",true);
                editor.apply();
                dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ثبت نام انجام نشد!", Toast.LENGTH_SHORT).show();
            }
        }) {//send to server

            Map<String, String> params;

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<>();
                params.put("nme",edt_name.getText().toString());
                params.put("family",edt_family.getText().toString());
                params.put("nmbr",edt_number.getText().toString());
                params.put("email", edt_email.getText().toString());
                params.put("password",edt_password.getText().toString());
                params.put("gender",sex);

                return params;
            }
        };//send to server

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);



    }//



    public interface Callback_Logup{
        void onRegistered(String username,String family);
    }


}
