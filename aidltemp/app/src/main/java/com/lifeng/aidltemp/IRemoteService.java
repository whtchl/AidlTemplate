package com.lifeng.aidltemp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class IRemoteService extends Service {



    public IRemoteService() {
    }

    /**
     * 当客户端绑定服务器时调用这个函数
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub(){

        @Override
        public int sum(int a, int b) throws RemoteException {
            Log.d("IRem oteService","收到了远处的请求，输入参数是："+a+" and  "+b);
            return a+b;
        }
    };
}
