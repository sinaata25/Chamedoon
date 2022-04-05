package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_Showlist_Bus;
import show_activitys.Choosed_Ticket_Bus;

public class Recycler_list_Bus_Adapter extends RecyclerView.Adapter<Recycler_list_Bus_Adapter.viewholder> {
    Context context;
    List<Model_Showlist_Bus>list;
    public Recycler_list_Bus_Adapter(Context context, List<Model_Showlist_Bus>list){
        this.list=list;
        this.context=context;

    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_adpter_bus_list,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Model_Showlist_Bus model=list.get(position);
        holder.price.setText(model.getPrice());
        holder.capacity.setText(model.getCapacity());
        holder.company.setText(model.getCompany());
        holder.type.setText(model.getType());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Choosed_Ticket_Bus.class);
                intent.putExtra("model_bus",model);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
            TextView date,time,price,type,company,capacity;
            CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.textView_date_bus_list);
            type=itemView.findViewById(R.id.textView_type_bus);
            time=itemView.findViewById(R.id.textView_time_train_list);
            company=itemView.findViewById(R.id.textView_company_bus);
            price=itemView.findViewById(R.id.textView_price_bus);
            capacity=itemView.findViewById(R.id.textView_capacity_train_list);
            cardView=itemView.findViewById(R.id.card_show_bus);
        }
    }






}
