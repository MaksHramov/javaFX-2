package com.example.slider;

public class SlideStatus {

    private final int currentIndex;
    private final double delay;

    SlideStatus(int currentIndex, double delay) {
        this.currentIndex = currentIndex;
        this.delay = delay;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public double getDelay() {
        return delay;
    }
}
