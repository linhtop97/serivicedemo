package vn.com.misa.servicedemo.screen.SystemService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Nguyen Ba Linh on 17/03/2019.
 */
public class MyPulicService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
