package com.bam.timerapp.view;

import com.bam.timerapp.responce.Notification;


import java.util.List;

public interface IViewContract {
    void updateTimer(String time);
    void updateNotifications(List<Notification> notificationList);
    void alarm(Notification notification);
    void start();
}
