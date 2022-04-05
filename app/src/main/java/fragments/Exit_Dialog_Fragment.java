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

public class Exit_Dialog_Fragment extends DialogFragment {
    View view;
    Button yes,no;
    public OnExitClicked onExitClicked;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        view= LayoutInflater.from(getContext()).inflate(R.layout.exit_dialog_fragment,null,false);
        setup();
        yes_handle();
        no_handle();
        builder.setView(view);
        return builder.create();
    }

    private void no_handle() {
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void yes_handle() {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onExitClicked!=null){
                    onExitClicked.clicked();
                }
                dismiss();
            }
        });
    }

    private void setup() {
        yes=view.findViewById(R.id.button_yes);
        no=view.findViewById(R.id.button_no);
    }
    public interface OnExitClicked{
        void clicked();
    }

}
