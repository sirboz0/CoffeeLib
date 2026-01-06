package main;

import main.Window;
import static org.lwjgl.opengl.GL46.*;
public class TestMain {
    static void main(String[] args) {

        Window window = new Window(1000, 800, "cool window");
        window.open();

        float[] vertices = {
                -0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f
        };

        int vao = HandyFuncs.makeVao();
        int vbo = HandyFuncs.makeBuffer(GL_ARRAY_BUFFER);

       HandyFuncs.sendBufferDataf(vertices, GL_ARRAY_BUFFER, GL_STATIC_DRAW);

        String vshadersrc = HandyFuncs.loadShader("src/main/shaders/vertexShader.glsl");
        String fshadersrc = HandyFuncs.loadShader("src/main/shaders/fragmentShader.glsl");

        int fshader = HandyFuncs.createShader(GL_FRAGMENT_SHADER, fshadersrc);
        int vshader = HandyFuncs.createShader(GL_VERTEX_SHADER, vshadersrc);
        int prog = HandyFuncs.createProgram(fshader, vshader);


        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        while (!window.WindowShouldClose()){
            window.clearBackground(0.3f, 0.5f, 0.4f, 1.0f);

            glUseProgram(prog);
            glBindVertexArray(vao);
            glDrawArrays(GL_TRIANGLES, 0, 3);

            window.update();
        }

    }
}
