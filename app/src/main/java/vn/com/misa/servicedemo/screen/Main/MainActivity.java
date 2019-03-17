package vn.com.misa.servicedemo.screen.Main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.com.misa.servicedemo.Navigator;
import vn.com.misa.servicedemo.R;
import vn.com.misa.servicedemo.Utils.Constant;
import vn.com.misa.servicedemo.databinding.ActivityMainBinding;
import vn.com.misa.servicedemo.screen.IntentService.IntentServiceActivity;
import vn.com.misa.servicedemo.screen.SystemService.SystemServiceActivity;
import vn.com.misa.servicedemo.screen.UnboundService.MyUnboundService;
import vn.com.misa.servicedemo.screen.UnboundService.UnboundServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mNavigator = new Navigator(this);
        initViews();
        initEvents();
        Intent intent = getIntent();
        if (intent != null) {
            String s = intent.getStringExtra(Constant.PHONE_NUMBER);
            if (s != null) {
                mBinding.tvFromClient.setText(s);
            }
        }
    }

    /**
     * Create by Nguyễn Bá Linh on 17/03/2019
     * Mô tả: khởi tạo các thuộc tính, giá trị ban đầu cho các biến, view
     */
    private void initViews() {
    }

    /**
     * Create by Nguyễn Bá Linh on 17/03/2019
     * Mô tả: Khởi tạo các sự kiện cho view khi người dùng tương tác
     */
    private void initEvents() {
        mBinding.btnIntentService.setOnClickListener(this);
        mBinding.btnSystemService.setOnClickListener(this);
        mBinding.btnUnBoundService.setOnClickListener(this);
        mBinding.btnBoundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIntentService:
                mNavigator.startActivity(IntentServiceActivity.class);
                break;
            case R.id.btnSystemService:
                mNavigator.startActivity(SystemServiceActivity.class);
                break;
            case R.id.btnUnBoundService:
                mNavigator.startActivity(UnboundServiceActivity.class);
                break;
            case R.id.btnBoundService:
                break;
            default:
                break;
        }
    }
}
