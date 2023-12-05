package org.armandosalazar;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Roblox {
    private long WINDOW;
    private final int width = 800;
    private final int height = 600;

    private final double[] centerProjection = new double[]{0, 10, 10};

    private void draw() {
        // TODO: Draw a builds 3D and sky, and cars and clouds usings me methods of draw
        // Sun
        Graphics.setColorRGB(255, 230, 0);
        Graphics.fillCircle(width / 2, height / 2, 100);
        Graphics.setColorRGB(255, 255, 0);
        Graphics.fillCircle(width / 2, height / 2, 90);

        // Clouds
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillEllipse(width / 2 - 200, height / 2 - 200, 100, 45);
        Graphics.fillEllipse(width / 2 - 150, height / 2 - 200, 100, 50);
        Graphics.fillEllipse(width / 2 - 100, height / 2 - 200, 100, 45);

        Graphics.fillEllipse(width / 2 - 400, height / 2 - 100, 100, 45);
        Graphics.fillEllipse(width / 2 - 350, height / 2 - 100, 100, 50);
        Graphics.fillEllipse(width / 2 - 300, height / 2 - 100, 100, 45);

        Graphics.fillEllipse(-width / 2 + 200, height / 2 - 180, 100, 45);
        Graphics.fillEllipse(-width / 2 + 150, height / 2 - 180, 100, 50);
        Graphics.fillEllipse(-width / 2 + 100, height / 2 - 180, 100, 45);

        // Street
        Graphics.setColorRGB(0, 0, 0);
        Graphics.fillRect(-width / 2, -height / 2, width, height / 2);
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(-width / 2, -height / 2, width, 10);
        Graphics.fillRect(-width / 2, -height / 2 + 50, width, 10);
        Graphics.fillRect(-width / 2, -height / 2 + 100, width, 10);


        // Buildings
        Graphics.setColorRGB(128, 128, 128); // Gray color
        double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        int scale = 30;

        for (int i = 0; i < points.length; i++) {
            double u = -centerProjection[2] / (points[i][2] - centerProjection[2]);
            points[i][0] = centerProjection[0] + (points[i][0] - centerProjection[0]) * u;
            points[i][1] = centerProjection[1] + (points[i][1] - centerProjection[1]) * u;

            points[i][0] *= scale;
            points[i][1] *= scale;
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
        }

        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[2][0], (int) points[6][0], (int) points[4][0]},
                new int[]{(int) points[0][1], (int) points[2][1], (int) points[6][1], (int) points[4][1]});
        Graphics.fillPolygon(new int[]{(int) points[1][0], (int) points[3][0], (int) points[7][0], (int) points[5][0]},
                new int[]{(int) points[1][1], (int) points[3][1], (int) points[7][1], (int) points[5][1]});
        Graphics.fillPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
        Graphics.fillPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]},
                new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[2][0], (int) points[6][0], (int) points[4][0]},
                new int[]{(int) points[0][1], (int) points[2][1], (int) points[6][1], (int) points[4][1]});
        Graphics.drawPolygon(new int[]{(int) points[1][0], (int) points[3][0], (int) points[7][0], (int) points[5][0]},
                new int[]{(int) points[1][1], (int) points[3][1], (int) points[7][1], (int) points[5][1]});
        Graphics.drawPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
        Graphics.drawPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]},
                new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});
        Graphics.drawLine((int) points[0][0], (int) points[0][1], (int) points[1][0], (int) points[1][1]);
        Graphics.drawLine((int) points[0][0], (int) points[0][1], (int) points[2][0], (int) points[2][1]);
        Graphics.drawLine((int) points[1][0], (int) points[1][1], (int) points[3][0], (int) points[3][1]);
        Graphics.drawLine((int) points[2][0], (int) points[2][1], (int) points[3][0], (int) points[3][1]);





    }


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

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        WINDOW = glfwCreateWindow(width, height, "Roblox", NULL, NULL);
        if (WINDOW == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(WINDOW, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        glfwMakeContextCurrent(WINDOW);
        glfwSwapInterval(1);
        glfwShowWindow(WINDOW);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }

    private void loop() {
        // glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black background
        glClearColor(135 / 255.0f, 206 / 255.0f, 235 / 255.0f, 1.0f); // Sky blue background
        glPointSize(2.0f);

        while (!glfwWindowShouldClose(WINDOW)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Use pixel coordinates centered at (0, 0)
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho((double) -width / 2, (double) width / 2, (double) -height / 2, (double) height / 2, 1, -1);
            glMatrixMode(GL_MODELVIEW);

            // TODO: Draw all here
            draw();

            glfwSwapBuffers(WINDOW);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Roblox().run();
    }
}
