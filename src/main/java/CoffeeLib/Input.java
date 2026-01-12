package CoffeeLib;
import static org.lwjgl.glfw.GLFW.*;
public class Input {
    long glfwWindow;

    // STOLE THIS FROM LWJGL CODE AND edited it lmao
    public static final int HAT_CENTERED = 0;
    public static final int HAT_UP = 1;
    public static final int HAT_RIGHT = 2;
    public static final int HAT_DOWN = 4;
    public static final int HAT_LEFT = 8;
    public static final int HAT_RIGHT_UP = 3;
    public static final int HAT_RIGHT_DOWN = 6;
    public static final int HAT_LEFT_UP = 9;
    public static final int HAT_LEFT_DOWN = 12;

    public static final int KEY_UNKNOWN = -1;
    public static final int KEY_SPACE = 32;
    public static final int KEY_APOSTROPHE = 39;
    public static final int KEY_COMMA = 44;
    public static final int KEY_MINUS = 45;
    public static final int KEY_PERIOD = 46;
    public static final int KEY_SLASH = 47;

    public static final int KEY_0 = 48;
    public static final int KEY_1 = 49;
    public static final int KEY_2 = 50;
    public static final int KEY_3 = 51;
    public static final int KEY_4 = 52;
    public static final int KEY_5 = 53;
    public static final int KEY_6 = 54;
    public static final int KEY_7 = 55;
    public static final int KEY_8 = 56;
    public static final int KEY_9 = 57;

    public static final int KEY_SEMICOLON = 59;
    public static final int KEY_EQUAL = 61;

    public static final int KEY_A = 65;
    public static final int KEY_B = 66;
    public static final int KEY_C = 67;
    public static final int KEY_D = 68;
    public static final int KEY_E = 69;
    public static final int KEY_F = 70;
    public static final int KEY_G = 71;
    public static final int KEY_H = 72;
    public static final int KEY_I = 73;
    public static final int KEY_J = 74;
    public static final int KEY_K = 75;
    public static final int KEY_L = 76;
    public static final int KEY_M = 77;
    public static final int KEY_N = 78;
    public static final int KEY_O = 79;
    public static final int KEY_P = 80;
    public static final int KEY_Q = 81;
    public static final int KEY_R = 82;
    public static final int KEY_S = 83;
    public static final int KEY_T = 84;
    public static final int KEY_U = 85;
    public static final int KEY_V = 86;
    public static final int KEY_W = 87;
    public static final int KEY_X = 88;
    public static final int KEY_Y = 89;
    public static final int KEY_Z = 90;

    public Input(Window window){
        this.glfwWindow = window.getGlfwWindow();
    }

    public boolean isKeyPressed(int key){
        boolean pressed = false;
        if (glfwGetKey(this.glfwWindow, key) == GLFW_PRESS){
            pressed = true;
        }
        return pressed;
    }

    public boolean isKeyReleased(int key){
        boolean released = false;
        if (glfwGetKey(this.glfwWindow, key) == GLFW_RELEASE){
            released = true;
        }
        return released;
    }
}
