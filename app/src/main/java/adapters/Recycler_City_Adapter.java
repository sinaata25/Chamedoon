package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.chamedoon.R;
import model.Model_Train_City;

public class Recycler_City_Adapter extends RecyclerView.Adapter<Recycler_City_Adapter.viewholder> {
    Context context;
    List<Model_Train_City>list;     Oncitypick oncitypick;
    public Recycler_City_Adapter(List<Model_Train_City>list, Context context,Oncitypick oncitypick){
        this.context=context;
        this.list=list;
        this.oncitypick=oncitypick;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_adapter_cities,parent,false);;
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Model_Train_City model=list.get(position);
        holder.txt.setText(model.getCity());
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oncitypick.pick(model.getCity());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView txt;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.textView_cityshow);
        }
    }


    public interface Oncitypick{
        void pick(String city);
    }




}
