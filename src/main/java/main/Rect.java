package main;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL46.*;


// TOFIX: fix set angle and angle not working it just ruins the rects matrix

public class Rect {
    // thoses are vertices and indices for square ONLY
    private final float[] vertices = {
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };
    private final int[] indices = {
            0, 1, 2,
            1, 3, 2
    };

    private float x;
    private float y;

    private float width;
    private float height;

    private float angle;


    int vao = HandyFuncs.makeVao();
    int vbo = HandyFuncs.makeBuffer(GL_ARRAY_BUFFER);
    int ebo = HandyFuncs.makeBuffer(GL_ELEMENT_ARRAY_BUFFER);

    // sprite class will have different shaders
    protected String vshadersrc = HandyFuncs.loadShader("src/main/shaders/vertexShader.glsl");
    protected String fshadersrc = HandyFuncs.loadShader("src/main/shaders/fragmentShader.glsl");

    int fshader = HandyFuncs.createShader(GL_FRAGMENT_SHADER, fshadersrc);
    int vshader = HandyFuncs.createShader(GL_VERTEX_SHADER, vshadersrc);
    int prog = HandyFuncs.createProgram(fshader, vshader);

    Matrix4f modelMatrix = HandyFuncs.makeMatrix(prog, "modelMatrix");

    Rect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        angle = 0.0f;



        HandyFuncs.sendBufferDataf(vertices, GL_ARRAY_BUFFER, GL_STATIC_DRAW);
        HandyFuncs.sendBufferDatai(indices, GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // update rect location using its positions
        this.setPosition(x, y);
        this.setSize(width, height);
        // transform works when i remove this?!?!?! bro wtf
        //this.setAngle(0.0f);


    }

    // sets the rect's position and updates
    void setPosition(float x, float y){
        this.x = x;
        this.y = y;

        modelMatrix.m30(x);
        modelMatrix.m31(y);
    }

    void setSize(float width, float height){
        this.width = width;
        this.height = height;

        modelMatrix.scale(width, height, 0.0f);
    }

    void setAngle(float angle){
        this.angle = angle;
        modelMatrix.rotationX(angle);
    }
    float getAngle(){
        return this.angle;
    }

    float getxPosition(){
        return this.x;
    }
    float getyPosition(){
        return this.y;
    }

    float getWidth(){
        return this.width;
    }
    float getHeight(){
        return this.height;
    }
}
