package com.example.slider;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private ImageView screen;

    @FXML
    private TextField speedField;

    private ConcreteAggregate conaggr;
    private Iterator iter;
    private SlideAnimation animation;

    @FXML
    public void initialize() {
        conaggr = new ConcreteAggregate("img");
        iter = conaggr.getIterator();

        animation = new SlideAnimation(screen, iter, 1000);
        animation.play();
    }

    @FXML
    public void nextSlide() {
        screen.setImage((Image) iter.next());
    }

    @FXML
    public void prevSlide() {
        screen.setImage((Image) iter.preview());
    }

    @FXML
    public void applySpeed() {
        try {
            double delay = Double.parseDouble(speedField.getText());
            animation.setDelay(delay);
        } catch (NumberFormatException e) {
            System.out.println("Неверная скорость");
        }
    }
}
