package com.my.liufeng.chat.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * 动画管理
 *
 * @author liufeng
 */
public class TimeLineManager {
    private static Timeline timeline = new Timeline();

    public static void addKeyFrame(KeyFrame keyFrame) {
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
