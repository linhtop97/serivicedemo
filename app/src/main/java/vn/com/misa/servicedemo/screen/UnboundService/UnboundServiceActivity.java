package vn.com.misa.servicedemo.screen.UnboundService;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.com.misa.servicedemo.R;
import vn.com.misa.servicedemo.databinding.ActivityUnboundServiceBinding;

/**
 * Created by Nguyen Ba Linh on 17/03/2019.
 */
public class UnboundServiceActivity extends AppCompatActivity {
    private ActivityUnboundServiceBinding mBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_unbound_service);
        mBinding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(UnboundServiceActivity.this, MyUnboundService.class));
            }
        });

        mBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(UnboundServiceActivity.this, MyUnboundService.class));

            }
        });
    }
}
