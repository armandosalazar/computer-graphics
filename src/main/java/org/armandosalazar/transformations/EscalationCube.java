package org.armandosalazar.transformations;


import org.armandosalazar.Graphics;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class EscalationCube {
    final double[] center = new double[]{2, 2, 5};
    private final int width = 400;
    private final int height = 600;
    private long WINDOW;
    private int coordinate = 0;

    private double scale = 10;
    private boolean flag = true;

    public static void main(String[] args) {
        new EscalationCube().run();
    }

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(WINDOW);
        glfwDestroyWindow(WINDOW);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
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

            // Draw a spiral 3D to 2D
            cube();

            glfwSwapBuffers(WINDOW);
            glfwPollEvents();
        }
    }

    private void cube() {
        // Draw a white pixel in the center of the screen
        Graphics.setColorRGB(0, 0, 0);

        final double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        if (scale > 40) {
            flag = false;
        } else if (scale < 10) {
            flag = true;
        }

        // Escalation cube
        if (flag) {
            scale += .08;
        } else {
            scale -= .08;
            System.out.println(scale);
        }

        for (int i = 0; i < points.length; i++) {
            double u = -center[2] / (points[i][2] - center[2]);
            points[i][0] = center[0] + (points[i][0] - center[0]) * u;
            points[i][1] = center[1] + (points[i][1] - center[1]) * u;

            points[i][0] *= scale;
            points[i][1] *= scale;
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
        }

        // Fill cube
        Graphics.setColorRGB(0, 255, 0);
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.fillPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.fillPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
        // Draw cube
        Graphics.setColorRGB(0, 0, 255);
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.drawPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.drawPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        WINDOW = glfwCreateWindow(width, height, "Cube", NULL, NULL);
        if (WINDOW == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(WINDOW, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_X && action == GLFW_RELEASE) {
                coordinate = 0;
            }
            if (key == GLFW_KEY_Y && action == GLFW_RELEASE) {
                coordinate = 1;
            }
            if (key == GLFW_KEY_Z && action == GLFW_RELEASE) {
                coordinate = 2;
            }
            if (key == GLFW_KEY_RIGHT_BRACKET) {
                center[coordinate] += .5;
            }
            if (key == GLFW_KEY_SLASH) {
                center[coordinate] -= .5;
            }
            if (key == GLFW_KEY_M && action == GLFW_RELEASE)
                System.out.println("{ x: " + center[0] + ", y:" + center[1] + ", z: " + center[2] + " }");
        });

        glfwMakeContextCurrent(WINDOW);
        glfwSwapInterval(1);
        glfwShowWindow(WINDOW);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }
}
