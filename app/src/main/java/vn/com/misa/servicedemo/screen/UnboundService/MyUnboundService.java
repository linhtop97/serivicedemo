package vn.com.misa.servicedemo.screen.UnboundService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Nguyen Ba Linh on 17/03/2019.
 */
public class MyUnboundService extends Service {

    private static final String TAG = "MyUnboundService";
    private MediaPlayer mPlayer;
    private boolean mIsAllowAlive;

    @Override
    public void onCreate() {
        super.onCreate();
        mIsAllowAlive = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (mIsAllowAlive){
                    Log.d(TAG, "running: " + i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
