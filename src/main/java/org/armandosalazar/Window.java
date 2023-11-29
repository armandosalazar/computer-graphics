package org.armandosalazar;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private long window;
    private final int width = 400;
    private final int height = 600;
    private final int[] windowWidth = new int[1];
    private final int[] windowHeight = new int[1];
    private final int[] framebufferWidth = new int[1];
    private final int[] framebufferHeight = new int[1];

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
//        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
//        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
//        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
//        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Hello World!", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        glfwGetWindowSize(window, windowWidth, windowHeight); // Get the window size passed to glfwCreateWindow
        System.out.println("Window size: " + windowWidth[0] + "x" + windowHeight[0]);
        glfwGetFramebufferSize(window, framebufferWidth, framebufferHeight); // Get the frame buffer size passed to glfwCreateWindow
        System.out.println("Framebuffer size: " + framebufferWidth[0] + "x" + framebufferHeight[0]);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }

    private void loop() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black background
        glPointSize(2.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Use pixel coordinates centered at (0, 0)
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho((double) -width / 2, (double) width / 2, (double) -height / 2, (double) height / 2, 1, -1);
            glMatrixMode(GL_MODELVIEW);

            // Draw a white pixel in the center of the screen
            glColor3f(1.0f, 1.0f, 1.0f);
            Graphics.drawLine(100, 100, -100, -100);
            Graphics.drawCircle(0, 0, 100);
            Graphics.drawRectangle(0, 0, 200, 20);
            Graphics.drawEllipse(0, 0, 200, 100);

            Graphics.setColorRGB(0, 225, 125);
            Graphics.drawTriangle(0, 0, 100, 100, 200, 0);

            Graphics.setColorRGB(252, 177, 3);
            Graphics.putPixel(0, 0);

            Graphics.drawPolygon(new int[]{0, 100, 200}, new int[]{0, 100, 0});
            Graphics.fillPolygon(new int[]{0, 100, 200}, new int[]{0, 100, 0});

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Window().run();
    }
}
