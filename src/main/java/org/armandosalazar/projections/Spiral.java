package org.armandosalazar.projections;


import org.armandosalazar.Graphics;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Spiral {
    private long WINDOW;
    private final int width = 400;
    private final int height = 600;
    private final int[] projectionVector = new int[]{0, 3, 30};
    private int coordinate = 0;

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(WINDOW);
        glfwDestroyWindow(WINDOW);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        WINDOW = glfwCreateWindow(width, height, "Hello World!", NULL, NULL);
        if (WINDOW == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(WINDOW, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_X && action == GLFW_RELEASE) {
                System.out.println("X: " + projectionVector[0]);
                coordinate = 0;
            }
            if (key == GLFW_KEY_Y && action == GLFW_RELEASE) {
                System.out.println("Y: " + projectionVector[1]);
                coordinate = 1;
            }
            if (key == GLFW_KEY_Z && action == GLFW_RELEASE) {
                System.out.println("Z: " + projectionVector[2]);
                coordinate = 2;
            }
            if (key == GLFW_KEY_RIGHT_BRACKET && action == GLFW_RELEASE) {
                switch (coordinate) {
                    case 0:
                        System.out.println("X: " + projectionVector[0]);
                        break;
                    case 1:
                        System.out.println("Y: " + projectionVector[1]);
                        break;
                    case 2:
                        System.out.println("Z: " + projectionVector[2]);
                        break;
                }
                projectionVector[coordinate]++;
            }
            if (key == GLFW_KEY_SLASH && action == GLFW_RELEASE) {
                switch (coordinate) {
                    case 0:
                        System.out.println("X: " + projectionVector[0]);
                        break;
                    case 1:
                        System.out.println("Y: " + projectionVector[1]);
                        break;
                    case 2:
                        System.out.println("Z: " + projectionVector[2]);
                        break;
                }
                projectionVector[coordinate]--;
            }
        });

        glfwMakeContextCurrent(WINDOW);
        glfwSwapInterval(1);
        glfwShowWindow(WINDOW);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }

    private void loop() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black background
        glPointSize(2.0f);

        while (!glfwWindowShouldClose(WINDOW)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Use pixel coordinates centered at (0, 0)
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho((double) -width / 2, (double) width / 2, (double) -height / 2, (double) height / 2, 1, -1);
            glMatrixMode(GL_MODELVIEW);

            // Draw a coordinate system 3D
            Graphics.setColorRGB(255, 255, 255);
            Graphics.drawLine(-width / 2, 0, width / 2, 0);
            Graphics.drawLine(0, -height / 2, 0, height / 2);
            Graphics.drawLine(-width / 2, -height / 2, width / 2, height / 2);

            // Draw a spiral 3D to 2D
            spiral();

            glfwSwapBuffers(WINDOW);
            glfwPollEvents();
        }
    }

    private void spiral() {
        // Draw a white pixel in the center of the screen
        Graphics.setColorRGB(0, 225, 125);

        int points = 1000;
        int scale = 50;

        float step = (float) (8 * Math.PI / points);
        for (float t = 0; t < 8 * Math.PI; t += step) {
            float u = -t / projectionVector[2];
            float x = (float) (Math.cos(t) + projectionVector[0] * u);
            float y = (float) (Math.sin(t) + projectionVector[1] * u);
            x *= scale;
            y *= scale;
            Graphics.putPixel((int) x, (int) y);
        }
    }

    public static void main(String[] args) {
        new Spiral().run();
    }
}
