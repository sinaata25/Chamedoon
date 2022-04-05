package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_Date_Pick;

public class Recycler_month_Adapter extends RecyclerView.Adapter<Recycler_month_Adapter.viewhldr> {
    List<Model_Date_Pick>list;
    Context context;
    OnMonth onMonth;
    public  Recycler_month_Adapter(List<Model_Date_Pick>list,Context context,OnMonth onMonth){
    this.context=context;
    this.list=list;
    this.onMonth=onMonth;
    }


    @NonNull
    @Override
    public viewhldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_adapter_month_pick,parent,false);
        return new viewhldr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhldr holder, int position) {
        final Model_Date_Pick model=list.get(position);
        holder.month.setText(model.getMonth());
        holder.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMonth.Onselected(model.getMonth());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewhldr extends RecyclerView.ViewHolder{
    TextView month;
        public viewhldr(@NonNull View itemView) {
            super(itemView);
            month=itemView.findViewById(R.id.textView_month);
        }
    }

    public interface OnMonth{
        void Onselected(String month);
    }


}
