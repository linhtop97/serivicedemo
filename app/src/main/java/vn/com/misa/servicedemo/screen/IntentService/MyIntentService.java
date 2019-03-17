package vn.com.misa.servicedemo.screen.IntentService;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import vn.com.misa.servicedemo.R;
import vn.com.misa.servicedemo.Utils.DataCallback;
import vn.com.misa.servicedemo.Utils.FetchStringFromUrl;

import static vn.com.misa.servicedemo.Utils.Constant.EXTRA_URL;

/**
 * Create by Nguyễn Bá Linh on 17/03/2019
 * Mô tả: Service lấy dữ liệu từ mạng
 */
public class MyIntentService extends IntentService implements DataCallback<String> {

    private static final String TAG = "MyIntentService";
    public static final String MY_SERVICE_MESSAGE = "MY_MESSAGE_SERVICE";
    public static final String MY_SERVICE_PALOAD = "MY_SERVICE_PALOAD";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MyIntentService() {
        super(TAG);
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        if (intent != null) {
            final String url = intent.getStringExtra(EXTRA_URL);
            if (url != null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new FetchStringFromUrl(getApplicationContext(), MyIntentService.this).getJSONStringFromURL(url);
                    }
                });
                thread.start();
            } else {
                Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDataSuccess(String data) {
        try {
            if (data != null) {
                Log.d(TAG, "onDataSuccess: " + data);
                Intent intent = new Intent(MY_SERVICE_MESSAGE);
                intent.putExtra(MY_SERVICE_PALOAD, data);
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
