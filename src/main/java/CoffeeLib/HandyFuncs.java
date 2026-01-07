package CoffeeLib;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;



import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;


import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.stb.STBImage.*;


public class HandyFuncs {

        public static class Image{
            ByteBuffer data;
            int w, h, c;
        }

        public static Image loadImage(String path){
            try(MemoryStack stack = MemoryStack.stackPush()){
                IntBuffer w = stack.mallocInt(1);
                IntBuffer h = stack.mallocInt(1);
                IntBuffer c = stack.mallocInt(1);

                ByteBuffer image = stbi_load(path, w, h, c, 0);
                if (image == null)
                    throw new RuntimeException(stbi_failure_reason());

                Image i = new Image();
                i.data = image;
                i.w = w.get(0);
                i.h = h.get(0);
                i.c = 4;
                return i;
            }
        }

        static String loadShader(String path){
            try{
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        static void sendBufferDataf(float[] data, int type, int drawType){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
            buffer.put(data);
            buffer.flip();

            glBufferData(type, buffer, drawType);
        }

        static void sendBufferDatai(int[] data, int type, int drawType){
            IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
            buffer.put(data);
            buffer.flip();

            glBufferData(type, buffer, drawType);
        }

        static int makeBuffer(int type){
            int buffer = glCreateBuffers();
            glBindBuffer(type, buffer);
            return buffer;
        }
        static int makeVao(){
            int vao = glCreateVertexArrays();
            glBindVertexArray(vao);
            return vao;
        }
        static int createShader(int type, String source){
            int shader = glCreateShader(type);
            glShaderSource(shader, source);
            glCompileShader(shader);
            return shader;
        }

        static int createProgram(int fShader, int vShader){
            int program = glCreateProgram();

            glAttachShader(program, fShader);
            glAttachShader(program, vShader);

            glLinkProgram(program);
            glUseProgram(program);

            return program;
        }

        // creates a texture and initlializes texture stuff with given args and returns the binded texture
        static int makeTexture(String imgPath, int textureNum, int textureD, int program, int size, int stride){
            int t = glCreateTextures(textureD);
            Image image = loadImage(imgPath);

            glActiveTexture(textureNum);
            glBindTexture(textureD, t);
            glTexParameteri(textureD, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(textureD, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            stbi_set_flip_vertically_on_load(false);

            glTexParameteri(textureD, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(textureD, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexImage2D(textureD, 0, GL_RGBA, image.w, image.h, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.data);
            stbi_image_free(image.data);
            glGenerateMipmap(textureD);

            glVertexAttribPointer(1, size, GL_FLOAT, false, stride * Float.BYTES, 0);// for texture
            glEnableVertexAttribArray(1);

            int tex0 = glGetUniformLocation(program, "tex");
            glUseProgram(program);
            glUniform1i(tex0, 0);
            return t;
        }

        // creates and initializes a new matrix4x4 and updates it, takes program and uniform matrix name
        static Matrix4f makeMatrix(int shaderProgram, String uniformName){
            int matloc = glGetUniformLocation(shaderProgram, uniformName);
            Matrix4f mat = new Matrix4f().identity();

            FloatBuffer b = BufferUtils.createFloatBuffer(16);
            mat.get(b);

            glUniformMatrix4fv(matloc, false, b);
            return mat;
        }

        // sends latest data to the matrix, takes matrix and its location to send to
        static void updateMatrix(Matrix4f matrixToUpdate, int matrixLocation){
            FloatBuffer b = BufferUtils.createFloatBuffer(16);
            matrixToUpdate.get(b);

            glUniformMatrix4fv(matrixLocation, false, b);
        }

    }


