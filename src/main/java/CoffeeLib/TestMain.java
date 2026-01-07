package CoffeeLib;

import CoffeeLib.Window;

public class TestMain {
    static void main(String[] args) {

        Window window = new Window(1000, 800, "cool window");
        window.open();


        Rect rect = new Rect(0.0f, 0.0f, 0.1f, 0.3f);
        //rect.setAngle(50.0f);

        while (!window.WindowShouldClose()){
            window.clearBackground(0.3f, 0.5f, 0.4f, 1.0f);


            window.draw(rect);


            window.update();
        }

    }
}