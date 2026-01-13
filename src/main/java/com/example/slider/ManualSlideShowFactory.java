package com.example.slider;

import com.example.slider.ConcreteAggregate;
import com.example.slider.Iterator;
import javafx.scene.image.ImageView;

public class ManualSlideShowFactory implements SlideShowFactory {

    @Override
    public ImageView createSlideShow() {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        ConcreteAggregate aggregate = new ConcreteAggregate("img");
        Iterator iterator = aggregate.getIterator();

        imageView.setImage((javafx.scene.image.Image) iterator.next());

        return imageView;
    }
}
