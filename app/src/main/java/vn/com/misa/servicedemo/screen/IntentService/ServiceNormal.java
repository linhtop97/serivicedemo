package vn.com.misa.servicedemo.screen.IntentService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import vn.com.misa.servicedemo.R;
import vn.com.misa.servicedemo.Utils.DataCallback;
import vn.com.misa.servicedemo.Utils.FetchStringFromUrl;

import static vn.com.misa.servicedemo.Utils.Constant.EXTRA_URL;

/**
 * Created by Nguyen Ba Linh on 17/03/2019.
 */
public class ServiceNormal extends Service implements DataCallback<String> {

    private static final String TAG = "ServiceNormal";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (intent != null) {
            final String url = intent.getStringExtra(EXTRA_URL);
            if (url != null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new FetchStringFromUrl(getApplicationContext(), ServiceNormal.this).getJSONStringFromURL(url);
                    }
                });
                thread.start();
            } else {
                Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy:");
        super.onDestroy();
    }

    @Override
    public void onDataSuccess(String data) {
        try {
            if (data != null) {
                Log.d(TAG, "onDataSuccess: " + data);
                Intent intent = new Intent(MyIntentService.MY_SERVICE_MESSAGE);
                intent.putExtra(MyIntentService.MY_SERVICE_PALOAD, data);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                return;
            }
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDataNotAvailable() {
        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
    }
}
