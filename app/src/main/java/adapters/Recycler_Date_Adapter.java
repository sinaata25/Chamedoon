package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_Date_Pick;

public class Recycler_Date_Adapter extends RecyclerView.Adapter<Recycler_Date_Adapter.viewholder> {

    Context context;
    List<Model_Date_Pick>list;
    OnDatePicked onDatePicked;
    public Recycler_Date_Adapter(List<Model_Date_Pick>list, Context context,OnDatePicked onDatePicked){
        this.context=context;
        this.list=list;
        this.onDatePicked=onDatePicked;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_adapter_datepick,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {
         final Model_Date_Pick model = list.get(position);
         holder.txt_day.setText(model.getDay());
         holder.txt_day.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onDatePicked.Onday(model.getDay());
             }
         });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView txt_day;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txt_day=itemView.findViewById(R.id.textView_day);
        }
    }


    public interface OnDatePicked{
        void Onday(String day);
    }



}
