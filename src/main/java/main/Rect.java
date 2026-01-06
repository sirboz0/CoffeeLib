package main;

public class Rect {
    // thoses are vertices and indices for square ONLY
    private final float[] vertices = {
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };
    private final int[] indices = {
            0, 1, 3,
            3, 2, 1
    };

    float x;
    float y;

    float width;
    float height;

    Rect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
