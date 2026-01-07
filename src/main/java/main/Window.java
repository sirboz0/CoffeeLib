package main;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
public class Window {
    int width, height;
    String title;


    private boolean open;
    // window temporarly public
    public long glfwWindow;

    Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        glfwInit();
        glfwWindow = glfwCreateWindow(width, height, title, 0, 0);

        if (glfwWindow == 0){
            throw new RuntimeException("coffee Error: glfw window was not able to be created");
        }
        glfwMakeContextCurrent(glfwWindow);
        GL.createCapabilities();
        glfwShowWindow(glfwWindow);

        // closed by default
        open = false;
    }
    // opens a window (there needs to be a main loop for it to stay open)
    void open(){
        open = true;
    }
    void close(){
        open = false;
        glfwSetWindowShouldClose(glfwWindow, true);
        glfwDestroyWindow(glfwWindow);
    }

    boolean WindowShouldClose(){
        return glfwWindowShouldClose(glfwWindow);
    }


    // takes a sprite object

    // draws a rectangle to the screen
    void draw(Rect rect){
        if (rect instanceof Rect){
            // draw just rect just normal draw
            HandyFuncs.updateMatrix(rect.modelMatrix, glGetUniformLocation(rect.prog, "modelMatrix"));
            glUseProgram(rect.prog);
            glBindVertexArray(rect.vao);
            glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        }else if (rect instanceof Sprite){
            Sprite sprite = (Sprite)rect;
            // draw sprite rect WITH textures
        }
    }

    void update(){
        glfwPollEvents();
        glfwSwapBuffers(glfwWindow);
    }

    void clearBackground(float r, float g, float b, float a){
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(r, g, b, a);
    }
}
