package com.example.slider;

public class SlideShowFactoryProvider {

    public static SlideShowFactory getFactory(boolean auto) {
        if (auto) {
            return new AutoSlideShowFactory();
        } else {
            return new ManualSlideShowFactory();
        }
    }
}
