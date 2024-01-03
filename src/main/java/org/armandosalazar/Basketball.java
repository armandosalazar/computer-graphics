package org.armandosalazar;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Basketball {
    private long WINDOW;
    private final int width = 800;
    private final int height = 600;
    private int coordinate = 0;
    private final double[] centerProjection = new double[]{2, 2, 5};

    private void draw() {
        // Coordinates 3d -> 2d
        ElBrayan.draw(centerProjection);
        // ElMoy.draw(centerProjection);
        //LaSaman.draw(centerProjection);
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

        WINDOW = glfwCreateWindow(width, height, "Basketball", NULL, NULL);
        if (WINDOW == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(WINDOW, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_X && action == GLFW_RELEASE) coordinate = 0;
            if (key == GLFW_KEY_Y && action == GLFW_RELEASE) coordinate = 1;
            if (key == GLFW_KEY_Z && action == GLFW_RELEASE) coordinate = 2;
            if (key == GLFW_KEY_RIGHT_BRACKET) centerProjection[coordinate] += .5;
            if (key == GLFW_KEY_SLASH) centerProjection[coordinate] -= .5;
            if (key == GLFW_KEY_SPACE && action == GLFW_RELEASE)
                System.out.println("{ x: " + centerProjection[0] + ", y:" + centerProjection[1] + ", z: " + centerProjection[2] + " }");
        });

        glfwMakeContextCurrent(WINDOW);
        glfwSwapInterval(1);
        glfwShowWindow(WINDOW);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }

    private void loop() {
        // glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black background
        // TODO: Select (7, 10, 56) as the background color
        glClearColor(7 / 255f, 10 / 255f, 56 / 255f, 0.0f); // Black background

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
        new Basketball().run();
    }
}
