package vn.com.misa.servicedemo.screen.IntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.com.misa.servicedemo.R;
import vn.com.misa.servicedemo.databinding.ActivityIntentServiceBinding;

import static vn.com.misa.servicedemo.Utils.Constant.EXTRA_URL;
import static vn.com.misa.servicedemo.Utils.Constant.URL_DEFAULT;

/**
 * Create by Nguyễn Bá Linh on 17/03/2019
 * Mô tả: màn hình demo cho intent service trong android
 */
public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityIntentServiceBinding mBinding;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String jsonMsg = intent.getStringExtra(MyIntentService.MY_SERVICE_PALOAD);
                if (jsonMsg != null) {
                    mBinding.tvJsonString.setText(jsonMsg);
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intent_service);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(MyIntentService.MY_SERVICE_MESSAGE));
        initEvents();
    }

    /**
     * Create by Nguyễn Bá Linh on 17/03/2019
     * Mô tả: Khởi tạo các sự kiện cho view khi người dùng tương tác
     */
    private void initEvents() {
        mBinding.btnClear.setOnClickListener(this);
        mBinding.btnServiceNormal.setOnClickListener(this);
        mBinding.btnIntentService.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIntentService:
                Intent intent = new Intent();
                intent.setClass(IntentServiceActivity.this, MyIntentService.class);
                intent.putExtra(EXTRA_URL, URL_DEFAULT);
                startService(intent);
                break;
            case R.id.btnServiceNormal:
                Intent intent2 = new Intent();
                intent2.setClass(IntentServiceActivity.this, ServiceNormal.class);
                intent2.putExtra(EXTRA_URL, URL_DEFAULT);
                startService(intent2);
                break;
            case R.id.btnClear:
                mBinding.tvJsonString.setText(R.string.empty);
                break;
            default:
                break;
        }
    }
}
