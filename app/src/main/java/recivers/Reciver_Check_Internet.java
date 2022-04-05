package recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Reciver_Check_Internet extends BroadcastReceiver {
    OnErorinConnection onErorinConnection;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null){
            onErorinConnection.onAnswer();
        }else {
            onErorinConnection.onAnswer_connected();
        }

    }

    public void setOnErorinConnection(OnErorinConnection onErorinConnection) {
        this.onErorinConnection = onErorinConnection;
    }

    public interface OnErorinConnection{
        void onAnswer();
        void onAnswer_connected();
    }


}
