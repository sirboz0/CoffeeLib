package CoffeeLib;
// a coffee lib file
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL46.*;




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


    protected int vao = HandyFuncs.makeVao();
    protected int vbo = HandyFuncs.makeBuffer(GL_ARRAY_BUFFER);
    protected int ebo = HandyFuncs.makeBuffer(GL_ELEMENT_ARRAY_BUFFER);

    // sprite class will have different shaders
    protected String vshadersrc = HandyFuncs.loadShader("src/main/shaders/vertexShader.glsl");
    protected String fshadersrc = HandyFuncs.loadShader("src/main/shaders/fragmentShader.glsl");

    protected int fshader = HandyFuncs.createShader(GL_FRAGMENT_SHADER, fshadersrc);
    protected int vshader = HandyFuncs.createShader(GL_VERTEX_SHADER, vshadersrc);
    protected int prog = HandyFuncs.createProgram(fshader, vshader);
    private Matrix4f modelMatrix = HandyFuncs.makeMatrix(prog, "modelMatrix");

    public Rect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        angle = 0.0f;



        HandyFuncs.sendBufferDataf(vertices, GL_ARRAY_BUFFER, GL_STATIC_DRAW);
        HandyFuncs.sendBufferDatai(indices, GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // update rect transform using its given values
        this.setPosition(x, y);
        this.setSize(width, height);
        this.setAngle(0.0f);
    }

    // sets the rect's position and updates(did it this way so i dont have to use translate and reset mat and make a mess)
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
        float rad = (float)Math.toRadians(angle);
        // works somehow
        modelMatrix.rotateAffineXYZ(0.0f, 0.0f, rad);
    }
    // special functions below-------------------------------

    // moves the rectangle every frame at a given xy speed
    void move(float x, float y){
        modelMatrix.translate(x, y, 0.0f);
    }

    // rotates the rectangle every frame at a given speed
    void rotate(float speed){
        this.angle += speed;
        // update angle
        setAngle(this.angle);
    }
    // getters below ----------------------------------------

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

    Matrix4f getModelMatrix(){return this.modelMatrix;}
    int getProgram(){return this.prog;}
}
