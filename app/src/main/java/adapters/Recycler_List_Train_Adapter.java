package adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_ShowList_Train;
import show_activitys.Choosed_Ticket_Train;

public class Recycler_List_Train_Adapter extends RecyclerView.Adapter<Recycler_List_Train_Adapter.viewholder> {

    List<Model_ShowList_Train>list;
    Context context;

    public Recycler_List_Train_Adapter(Context context, List<Model_ShowList_Train>list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.view_adapter_train_list_show,parent,false);
       return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Model_ShowList_Train model=list.get(position);
        holder.start_time.setText(model.getStart_time());
        holder.end_time.setText(model.getEnd_time());
        holder.capacity.setText(model.getCapacity());
        holder.price.setText(model.getPrice()+"ریال");
        holder.company.setText(model.getCompany());
        holder.type.setText(model.getType());
        holder.date.setText(model.getDate());
        holder.coupe_capacity.setText(" کوپه ای"+model.getCoupe_capacity()+"نفره ");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Choosed_Ticket_Train.class);
                intent.putExtra("Model_ShowList_Train",model);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView type,date,coupe_capacity,capacity,price,company,start_time,end_time;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.textView_type_trainlist);
            date=itemView.findViewById(R.id.textView_date_train_list);
            coupe_capacity=itemView.findViewById(R.id.textView_coupecapacity_trainlist);
            capacity=itemView.findViewById(R.id.textView_capacity_train);
            price=itemView.findViewById(R.id.textView_price_trainlist);
            company=itemView.findViewById(R.id.textView_company_trainlist);
            start_time=itemView.findViewById(R.id.textView_start_time_train);
            end_time=itemView.findViewById(R.id.textView_endtime_train);
            cardView=itemView.findViewById(R.id.card_train_show_list);
        }
    }



}
