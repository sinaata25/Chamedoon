package ataie.sina.chamedoon.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.Recycler_Dashbord_Adapter;
import ataie.sina.chamedoon.R;
import fragments.Exit_Dialog_Fragment;
import fragments.Logup_Fragment_Dialog;
import fragments.Vorood_Fragment_Dialog;
import model.Model_Dashbord_Fragment;
import show_activitys.Edit_Activity;

public class DashboardFragment extends Fragment {
    List<Model_Dashbord_Fragment>list;
    RecyclerView recyclerView;
    TextView textView_account,textView_name_dashbord,textview_exit,textview_edit,textview_money;
    Context context;    View root;
    ConstraintLayout constraintLayout;
    private static final int Edit_Request = 1008;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    SharedPreferences sharedPreferences;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setup_views();
        setdashbord();
        ////////////////////
        sharedPreferences=getContext().getSharedPreferences("log",Context.MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        String name=sharedPreferences.getString("name","");
        String family=sharedPreferences.getString("family","");
        if(email.equals("")) {
        login_logup_handle();
        }else{
            textView_name_dashbord.setText(name+" "+family);
            constraintLayout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
        }
        ///////////////////
        logout();
        edit();
        return root;
    }

    private void edit() {
        textview_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Edit_Activity.class);
                startActivityForResult(intent,Edit_Request);
            }
        });
    }


    private void logout() {
        textview_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exit_Dialog_Fragment exit_dialog_fragment=new Exit_Dialog_Fragment();
                FragmentManager fragmentManager=getParentFragmentManager();
                exit_dialog_fragment.onExitClicked=new Exit_Dialog_Fragment.OnExitClicked() {
                    @Override
                    public void clicked() {
                        sharedPreferences=getContext().getSharedPreferences("log",getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("name","");
                        editor.putString("family","");
                        editor.putString("email","");
                        editor.putString("number","");
                        editor.putString("sex","");
                        editor.putString("password","");
                        editor.putString("money","");
                        editor.putBoolean("loged_ok",false);
                        constraintLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.GONE);
                        linearLayout4.setVisibility(View.GONE);
                        editor.apply();
                    }
                };
                exit_dialog_fragment.show(fragmentManager,null);
            }
        });
    }

    public void setdashbord()
    {   recyclerView=(RecyclerView)root.findViewById(R.id.recyclerview_dashbord);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        list=new ArrayList<>();
        Model_Dashbord_Fragment model=new Model_Dashbord_Fragment();
        model.setTitle("لیست مسافران");
        model.setDrawable(R.drawable.passengers_icon);

        Model_Dashbord_Fragment model1=new Model_Dashbord_Fragment();
        model1.setTitle("سوابق تراکنش");
        model1.setDrawable(R.drawable.sabeghe_icon);

        Model_Dashbord_Fragment model2=new Model_Dashbord_Fragment();
        model2.setTitle("خرید های من");
        model2.setDrawable(R.drawable.byed_icon);

        list.add(model);
        list.add(model1);
        list.add(model2);
        recyclerView.setAdapter(new Recycler_Dashbord_Adapter(context,list));

    }

    public void setup_views(){
        constraintLayout=root.findViewById(R.id.login_logup);
        textView_account=root.findViewById(R.id.textView_dashbord_account);
        textview_edit=root.findViewById(R.id.edit_Profile_dashbord);
        textview_exit=root.findViewById(R.id.exit_from_profile_dashbord);
        textview_money=root.findViewById(R.id.textView_etebar_dashbord);
        textView_name_dashbord=root.findViewById(R.id.textView_name_loged);
        linearLayout1=root.findViewById(R.id.linear_signed);
        linearLayout2=root.findViewById(R.id.linear_signed_1);
        linearLayout3=root.findViewById(R.id.linear_signed_2);
        linearLayout4=root.findViewById(R.id.linear_signed_3);

    }

    public void onRegisterClicked(){
                Logup_Fragment_Dialog logup_fragment_dialog=new Logup_Fragment_Dialog();
                logup_fragment_dialog.callback_logup=new Logup_Fragment_Dialog.Callback_Logup() {
                    @Override
                    public void onRegistered(String username, String family) {
                        textView_name_dashbord.setText(username+" "+family);
                        constraintLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                    }
                };
                FragmentManager fragmentManager=getParentFragmentManager();
                logup_fragment_dialog.show(fragmentManager,null);
    }

    private void login_logup_handle(){
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Vorood_Fragment_Dialog dialog = new Vorood_Fragment_Dialog();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    dialog.callback=new Vorood_Fragment_Dialog.Callback() {
                        @Override
                        public void Onloginclicked(String name,String family) {
                            textView_name_dashbord.setText(name+" "+family);
                            constraintLayout.setVisibility(View.GONE);
                            linearLayout1.setVisibility(View.VISIBLE);
                            linearLayout2.setVisibility(View.VISIBLE);
                            linearLayout3.setVisibility(View.VISIBLE);
                            linearLayout4.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void Onregisterclicked() {
                                    onRegisterClicked();
                        }
                    };
                    dialog.show(fragmentManager, null);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==Edit_Request && resultCode==-1 && data!=null){
            textView_name_dashbord.setText(data.getExtras().getString("name")+" "+data.getExtras().getString("family"));
        }
    }
}