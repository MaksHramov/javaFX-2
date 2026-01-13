package com.example.slider;

public class SlideStatusBuilder {

    private int currentIndex = 1;
    private double delay = 1000;

    public SlideStatusBuilder withCurrentIndex(int index) {
        this.currentIndex = index;
        return this;
    }

    public SlideStatusBuilder withDelay(double delay) {
        this.delay = delay;
        return this;
    }

    public SlideStatus build() {
        return new SlideStatus(currentIndex, delay);
    }
}
