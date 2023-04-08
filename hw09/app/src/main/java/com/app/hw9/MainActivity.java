package com.app.hw9;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.hw9.databinding.ActivityMainBinding;
import com.app.hw9.services.MyService4;
import com.app.hw9.services.MyService5Async;
import com.app.hw9.services.MyService6;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    public ActivityMainBinding binding;
    public ClickHandlerMainActivity handlers = new ClickHandlerMainActivity(this);
    TextView txtMsg;
    Intent intentCallService4, intentCallService5, intentCallService6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        txtMsg = binding.result;
        binding.setClickHandler(handlers);
        intentCallService4 = new Intent(this, MyService4.class);
        intentCallService5 = new Intent(this, MyService5Async.class);
        intentCallService6 = new Intent(this, MyService6.class);
        locationPermissionRequest.launch(new String[] {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        });

        IntentFilter filter5 = new IntentFilter("matos.action.GOSERVICE5");
        IntentFilter filter6 = new IntentFilter("matos.action.GPSFIX");
        receiver = new MyEmbeddedBroadcastReceiver();

        registerReceiver(receiver, filter5);
        registerReceiver(receiver, filter6);

    }
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                android.Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,false);
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                        } else {
                            // No location access granted.
                        }
                    }
            );


    public class ClickHandlerMainActivity{
        Context context;
        public ClickHandlerMainActivity(Context context){ this.context = context; }
        public void btn1aClick(View view){
            Log.e("MAIN", "onClick: starting service4");
            startService(intentCallService4);
        }
        public void btn1bClick(View view){
            Log.e("MAIN", "onClick: stopping service4");
            stopService(intentCallService4);
        }

        public void btn2aClick(View view){
            Log.e("MAIN", "onClick: starting service5");
            startService(intentCallService5);
        }
        public void btn2bClick(View view){
            Log.e("MAIN", "onClick: stopping service5");
            stopService(intentCallService5);
        }
        public void btn3aClick(View view){
            Log.e("MAIN", "onClick: starting service6");
            startService(intentCallService6);
        }
        public void btn3bClick(View view){
            Log.e("MAIN", "onClick: stopping service6");
            stopService(intentCallService6);
        }
    }

    public class MyEmbeddedBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("MAIN>>", "ACTION: " + intent.getAction());
            if (intent.getAction().equals("matos.action.GOSERVICE5")) {
                String service5Data = intent.getStringExtra("MyService5DataItem");
                Log.e("MAIN>>", "Data received from Service5: " + service5Data);
                txtMsg.append("\nService5Data: > " + service5Data);
            }
            else if (intent.getAction().equals("matos.action.GPSFIX")) {
                double latitude = intent.getDoubleExtra("latitude", -1);
                double longitude = intent.getDoubleExtra("longitude", -1);
                String provider = intent.getStringExtra("provider");
                String service6Data = provider + " lat: " + Double.toString(latitude)
                        + " lon: " + Double.toString(longitude);
                Log.e("MAIN>>", "Data received from Service6:" + service6Data);
                txtMsg.append("\nService6Data: > "+ service6Data);
            }
        }
    }
}