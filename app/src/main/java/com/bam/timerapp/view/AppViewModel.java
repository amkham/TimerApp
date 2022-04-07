package com.bam.timerapp.view;

import androidx.lifecycle.ViewModel;

import com.bam.timerapp.presenter.Presenter;
import com.bam.timerapp.responce.Notification;

public class AppViewModel extends ViewModel {

    String host, name, msg;
    int port, hour, min , sec;

    private Presenter __presenter = new Presenter();

    public Presenter getPresenter() {
        return __presenter;
    }

    public void setConnectData(String host, int port){
       __presenter.createConnection(host, port);
    }

    public void createNotification(String name, String msg, int hour, int min, int sec){

        __presenter.createNotification(name, msg, hour, min, sec);

    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
