package com.jdjz.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by happen on 2016/8/28.
 */
public class IRemoteService extends Service {
    /**
     * 当客户端绑定服务器时调用这个函数
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
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
