package com.lsy.listeners;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<SpringApplicationEvent> {
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        System.out.println("my listener run!!!");
    }
}
