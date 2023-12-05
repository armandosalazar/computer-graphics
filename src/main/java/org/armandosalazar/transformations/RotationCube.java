package org.armandosalazar.transformations;


import org.armandosalazar.Graphics;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class RotationCube {
    private long WINDOW;
    private final int width = 400;
    private final int height = 600;
    private int coordinate = 0;
    final double[] center = new double[]{2, 2, 5};
    private int angle = 0;
    public void run() {
        init();
        loop();

        glfwFreeCallbacks(WINDOW);
        glfwDestroyWindow(WINDOW);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    public static void main(String[] args) {
        new RotationCube().run();
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
        final double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        // Escalar puntos
        int scale = 30;
        for (int i = 0; i < points.length; i++) {
            double u = -center[2] / (points[i][2] - center[2]);
            points[i][0] = center[0] + (points[i][0] - center[0]) * u;
            points[i][1] = center[1] + (points[i][1] - center[1]) * u;

            points[i][0] *= scale;
            points[i][1] *= scale;
        }

        double[][] rotatedPoints = Graphics.rotateCube(points, Math.toRadians(0), Math.toRadians(0), Math.toRadians(angle));

        // Fill cube
        Graphics.setColorRGB(0, 0, 250);
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[3][0], (int) rotatedPoints[2][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[3][1], (int) rotatedPoints[2][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[4][0], (int) rotatedPoints[5][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[4][1], (int) rotatedPoints[5][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[5][0], (int) rotatedPoints[4][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[5][1], (int) rotatedPoints[4][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[2][0], (int) rotatedPoints[3][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[2][1], (int) rotatedPoints[3][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});

        // Draw cube
        Graphics.setColorRGB(255, 0, 0);
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[3][0], (int) rotatedPoints[2][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[3][1], (int) rotatedPoints[2][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[4][0], (int) rotatedPoints[5][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[4][1], (int) rotatedPoints[5][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[5][0], (int) rotatedPoints[4][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[5][1], (int) rotatedPoints[4][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[2][0], (int) rotatedPoints[3][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[2][1], (int) rotatedPoints[3][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});

        if (angle > 360) {
            angle = 0;
        } else {
            angle++;
        }


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
