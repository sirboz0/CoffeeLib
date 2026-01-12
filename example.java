package Main;

import CoffeeLib.Window;
import CoffeeLib.Rect;
// sets up a window and makes new rect and draws it
public class TestMain {
    public static void main(String[] args) {

        Window window = new Window(1000, 800, "cool window");
        window.open();


        Rect rect = new Rect(0.0f, 0.0f, 0.3f, 0.3f);
        

        while (!window.WindowShouldClose()){
            window.clearBackground(0.3f, 0.5f, 0.4f, 1.0f);


            window.draw(rect);


            window.update();
        }

    }
}
