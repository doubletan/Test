package com.lenovo.test;

import org.greenrobot.eventbus.EventBus;

public class MainVM {
    public static void Change(final String f, final String l){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user=new User();
                user.firstName.set(f);
                user.lastName.set(l);
                EventBus.getDefault().post(user);
            }
        }).start();
    }
}
