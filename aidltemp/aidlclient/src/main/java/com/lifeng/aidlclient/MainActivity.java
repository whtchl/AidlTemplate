package com.lifeng.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lifeng.aidltemp.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText a;
    private EditText b;
    private EditText result;
    private Button btn;

    IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        //拿到远处服务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TAG","onServiceConnected");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindService();
    }
    private void initView() {
        Log.d("TAG","initView");
        a = (EditText) findViewById(R.id.sumA);
        b = (EditText) findViewById(R.id.sumB);
        result = (EditText) findViewById(R.id.result);
        result.setKeyListener(null);
        btn = (Button) findViewById(R.id.btn_cal);
        btn.setOnClickListener(this);
    }

    private void bindService() {
        Log.d("TAG","bindService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.lifeng.aidltemp", "com.lifeng.aidltemp.IRemoteService"));


        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {

        int aa = Integer.parseInt(a.getText().toString());
        int bb = Integer.parseInt(b.getText().toString());
        int rel;
        try {
            rel = iMyAidlInterface.sum(aa, bb);
            result.setText(rel+"");
        } catch (RemoteException e) {
            e.printStackTrace();
            result.setText("Error");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }


}
