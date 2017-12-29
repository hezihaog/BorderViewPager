package com.hzh.border.pager.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Package: com.hzh.borderviewpager.event
 * FileName: CheckPlateEvent
 * Date: on 2017/12/29  下午3:47
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class CheckPlateEvent {
    private int position;

    public int getPosition() {
        return position;
    }

    public CheckPlateEvent setPosition(int position) {
        this.position = position;
        return this;
    }

    public static void send(int position) {
        CheckPlateEvent event = new CheckPlateEvent();
        event.setPosition(position);
        EventBus.getDefault().post(event);
    }
}