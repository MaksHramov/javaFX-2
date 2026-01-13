package com.example.slider;

import javafx.scene.image.Image;

public class ConcreteAggregate implements Aggregate {

    private final String filetopic;

    public ConcreteAggregate(String filetopic) {
        this.filetopic = filetopic;
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    // ================= ВНУТРЕННИЙ ИТЕРАТОР =================
    private class ImageIterator implements Iterator {

        private int current = 0;

        // Получение изображения из resources
        private Image getImage(int index) {
            try {
                String path = "/img/" + filetopic + index + ".jpg";
                var stream = ConcreteAggregate.class.getResourceAsStream(path);
                if (stream == null) return null;
                return new Image(stream);
            } catch (Exception e) {
                return null;
            }
        }

        // Проверка существования следующего изображения
        @Override
        public boolean hasNext(int i) {
            Image img = getImage(current + i);
            return img != null && !img.isError();
        }

        // ================= ШАГ 4 =================
        @Override
        public Object next() {
            if (hasNext(1)) {
                current++;
                return getImage(current);
            }
            // Зацикливание
            current = 1;
            return getImage(current);
        }

        // ================= ШАГ 5 =================
        @Override
        public Object preview() {
            if (current <= 1) {
                int i = 1;
                while (getImage(i) != null) {
                    i++;
                }
                current = i - 1; // последний слайд
            } else {
                current--;
            }
            return getImage(current);
        }
    }
}
