package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import ataie.sina.chamedoon.R;
import model.Model_Dashbord_Fragment;

public class Recycler_Dashbord_Adapter extends RecyclerView.Adapter<Recycler_Dashbord_Adapter.Recycler_Holder> {
    Context context;
    List<Model_Dashbord_Fragment>list;
    ImageView image;
    TextView textView;

    public Recycler_Dashbord_Adapter(Context context, List<Model_Dashbord_Fragment>list) {
        this.list=list;
        this.context=context;
    }


    @NonNull
    @Override
    public Recycler_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_adapter_recycle_dashbord,parent,false);
        return new Recycler_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Holder holder, int position) {
    Model_Dashbord_Fragment model=list.get(position);
    textView.setText(model.getTitle());
    image.setImageResource(model.getDrawable());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Recycler_Holder extends RecyclerView.ViewHolder {
        public Recycler_Holder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView_dashbord);
            image=(ImageView)itemView.findViewById(R.id.imageView_dashbord);
        }
    }




}
