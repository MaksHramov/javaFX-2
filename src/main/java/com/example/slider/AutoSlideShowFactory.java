package com.example.slider;

import javafx.scene.image.ImageView;

public class AutoSlideShowFactory implements SlideShowFactory {

    @Override
    public ImageView createSlideShow() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        ConcreteAggregate aggregate = new ConcreteAggregate("img");
        Iterator iterator = aggregate.getIterator();

        SlideAnimation animation = new SlideAnimation(imageView, iterator, 1000);
        animation.play();

        return imageView;
    }
}
