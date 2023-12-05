package org.armandosalazar;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Bomberman {
    private long window;
    private final int width = 496 * 2; // 31 = 16x16
    private final int height = 208 * 2; // 13 = 16x16
    // Terrain 31:13
    private final int[][] terrain = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 3, 0, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 4, 1},
            {1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1},
            {1, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1},
            {1, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 4, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 2, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 2, 1, 2, 1, 0, 1, 2, 1, 0, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 2, 0, 0, 2, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    // Bomberman coordinates
    private int bombermanX = -5;
    private int bombermanY = 0;
    private int direction = 2; // 1 = up, 2 = down
    private boolean state = true;
    private long lastTime;
    int counterTimeBomb = 0;

    public void run() {
        lastTime = System.currentTimeMillis();

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

        window = glfwCreateWindow(width, height, "Bomberman", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // Close window
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            // Move bomberman
            if (key == GLFW_KEY_UP && action == GLFW_RELEASE) {
                bombermanY += 5;
                direction = 1;
            }
            if (key == GLFW_KEY_DOWN && action == GLFW_RELEASE) {
                bombermanY -= 5;
                direction = 2;
            }
            if (key == GLFW_KEY_RIGHT && action == GLFW_RELEASE) {
                bombermanX += 5;
                direction = 3;
            }
            if (key == GLFW_KEY_LEFT && action == GLFW_RELEASE) {
                bombermanX -= 5;
                direction = 4;
            }
        });

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        // Important! Without this line, LWJGL3 won't work!
        GL.createCapabilities(); // this line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.

    }

    private void loop() {
        glClearColor(0.21f, 0.52f, 0.0f, 0.0f);
        glPointSize(2.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            // Use pixel coordinates centered at (0, 0)
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho((double) -width / 2, (double) width / 2, (double) -height / 2, (double) height / 2, 1, -1);
            glMatrixMode(GL_MODELVIEW);

            // Draw a white pixel in the center of the screen
            Animation.bomberman(bombermanX, bombermanY, direction);

            // Animation.bomb(10, -100, stateBomb);
            long currentTime = System.currentTimeMillis();
            // 1 second
            long delay = 250;
            if (currentTime - lastTime > delay) {
                state = !state;
                lastTime = currentTime;
                if (bombermanY > -45 && bombermanX == -5) {
                    direction = 2;
                    bombermanY -= 5;
                }
                System.out.println("Bomberman: " + bombermanX + ", " + bombermanY);
                if (bombermanY == -45) {
                    direction = 3;
                    bombermanX += 5;
                }
                if (bombermanX == 250) {
                    direction = 1;
                    bombermanY += 5;
                }

                counterTimeBomb++;
                if (counterTimeBomb == 4) {
                    counterTimeBomb = 0;
                    int step = 0;
                    for (int i = 0; i < 13; i++) {
                        for (int j = 0; j < 31; j++) {
                            // Bom
                            if (terrain[i][j] == 4) {
                                terrain[i][j] = 5;
                            }
                            if (terrain[i][j] == 3 && terrain[i][j + 1] == 0 && step == 0) {
                                step = 1;
                                terrain[i][j + 1] = 3;
                                terrain[i][j] = 0;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < 13; i++) {
                        for (int j = 0; j < 31; j++) {
                            if (terrain[i][j] == 5) {
                                terrain[i][j] = 4;
                            }
                        }
                    }
                }
            }

            // Draw terrain
            Animation.terrain(terrain, state);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Bomberman().run();
    }
}
