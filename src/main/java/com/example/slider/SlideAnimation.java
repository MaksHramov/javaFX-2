package com.example.slider;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SlideAnimation {

    private final ImageView screen;
    private final Iterator iterator;
    private Timeline timeline;
    private double delay;

    public SlideAnimation(ImageView screen, Iterator iterator, double delay) {
        this.screen = screen;
        this.iterator = iterator;
        this.delay = delay;
        createTimeline();
    }

    private void createTimeline() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(delay), e -> {
                    // ❗ БЕЗ hasNext — итератор сам зацикливается
                    screen.setImage((Image) iterator.next());
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void setDelay(double delay) {
        this.delay = delay;
        timeline.stop();
        timeline.getKeyFrames().clear();
        createTimeline();
        timeline.play();
    }
}
