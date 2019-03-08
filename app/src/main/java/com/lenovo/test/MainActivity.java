package com.lenovo.test;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lenovo.test.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        initViews();
        setListener();
    }

    //监听
    private void setListener() {
        binding.dpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainVM.Change(user.firstName.get(),user.lastName.get());
            }
        });
        binding.zsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainVM.Change("21","张三");
            }
        });
        binding.lsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainVM.Change("35","李四");
            }
        });
    }

    //初始化
    private void initViews() {
        user=new User();
        user.firstName.set("30");
        user.lastName.set("张三");
        binding.setUser(user);

        //eventbus注册
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(User event) {
        user.firstName.set(event.firstName.get());
        user.lastName.set(event.lastName.get());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
