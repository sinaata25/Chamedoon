package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_Chairs;
import show_activitys.Chair_Bus;

public class Recycler_chair extends RecyclerView.Adapter<Recycler_chair.holder> {
        List<Model_Chairs>list;
        Context context;
    public Recycler_chair(List<Model_Chairs>list, Context context){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.rv_chair,parent,false);
       return new holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final Model_Chairs model_chairs=list.get(position);
        ///chair left
        holder.textView_1.setText(model_chairs.getSituation_left());
        if(model_chairs.getRezerved_left().equals("0")){///
                holder.textView_1.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_empty));
         ///
        }else{
                holder.textView_1.setContentDescription("rezerved");
            if(model_chairs.getGender_left().equals("male")){
                holder.textView_1.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_5));
            }else {
                holder.textView_1.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_woman));
            }
        }
        ///chair middle
        holder.textView_2.setText(model_chairs.getSituation_mid());
        if(model_chairs.getRezerved_mid().equals("0")){
            holder.textView_2.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_empty));
        }else{
            holder.textView_2.setContentDescription("rezerved");
            if(model_chairs.getGender_mid().equals("male")){
                holder.textView_2.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_5));
            }else {
                holder.textView_2.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_woman));
            }
        }
        ///char right
        holder.textView_3.setText(model_chairs.getSituation_right());
        if(model_chairs.getRezerved_right().equals("0")){
            holder.textView_3.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_empty));
        }else{
            holder.textView_3.setContentDescription("rezerved");
            if(model_chairs.getGender_right().equals("male")){
                holder.textView_3.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_5));
            }else {
                holder.textView_3.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_style_chair_woman));
            }
        }
        ///////////set on clicks
        ///textview1
        if(!holder.textView_1.getContentDescription().equals("rezerved")) {
            holder.textView_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.textView_1.getContentDescription().equals("notselected")) {
                        holder.textView_1.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_selected));
                        holder.textView_1.setContentDescription("selected");
                    } else {
                        holder.textView_1.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_chair_empty));
                        holder.textView_1.setContentDescription("notselected");
                    }
                }
            });
        }
        ///textview2
        if(!holder.textView_2.getContentDescription().equals("rezerved")) {
            holder.textView_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.textView_2.getContentDescription().equals("notselected")) {
                        holder.textView_2.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_selected));
                        holder.textView_2.setContentDescription("selected");
                    } else {
                        holder.textView_2.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_chair_empty));
                        holder.textView_2.setContentDescription("notselected");
                    }
                }
            });

        }
        ///textview3
        if(!holder.textView_3.getContentDescription().equals("rezerved")) {
            holder.textView_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.textView_3.getContentDescription().equals("notselected")) {
                        holder.textView_3.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_selected));
                        holder.textView_3.setContentDescription("selected");
                    } else {
                        holder.textView_3.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_style_chair_empty));
                        holder.textView_3.setContentDescription("notselected");
                    }
                }
            });


        }
        /////////


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView textView_1,textView_2,textView_3;
        public holder(@NonNull View itemView) {
            super(itemView);
            textView_1=itemView.findViewById(R.id.textView3);
            textView_2=itemView.findViewById(R.id.textView6);
            textView_3=itemView.findViewById(R.id.textView20);
        }
    }





}
