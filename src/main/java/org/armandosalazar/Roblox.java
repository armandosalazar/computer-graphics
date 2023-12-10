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
    private int coordinate = 0;

    private final double[] centerProjection = new double[]{2, 2, 5};
    private int angle = 0;
    private int scale = 30;

    private boolean flag = true;

    private void draw() {
        // TODO: Draw the moon white and the sky blue
        Graphics.setColorRGB(197, 219, 222);
        Graphics.fillCircle(0, 100, 150);
        // TODO: Draw circles in the moon to make it look like craters color gray
        Graphics.setColorRGB(173, 213, 219);
        Graphics.fillCircle(-100, 100, 10);
        Graphics.fillCircle(-50, 150, 20);
        Graphics.fillCircle(50, 150, 20);

        // TODO: Draw random stars white
        Graphics.setColorRGB(255, 255, 255);
        Graphics.putPixel((int) (Math.random() * width - width / 2), (int) (Math.random() * height - height / 2));
        Graphics.putPixel((int) (Math.random() * width - width / 2), (int) (Math.random() * height - height / 2));
        Graphics.putPixel((int) (Math.random() * width - width / 2), (int) (Math.random() * height - height / 2));
        Graphics.putPixel((int) (Math.random() * width - width / 2), (int) (Math.random() * height - height / 2));

        // TODO: Draw the spacecraft
        Graphics.setColorRGB(128, 128, 128); // Gray color
        double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};
        double[][] pointsWingsLeft = {{.0, 1, 1}, {.5, 2, 1}, {2, 1, 1}, {2, 2, 1}, {.0, 1, 2}, {.5, 2, 2}, {2, 1, 2}, {2, 2, 2}};
        double[][] pointsWingsRight = {{1, 1, 1}, {1.5, 2, 1}, {4, 1, 1}, {3.5, 2, 1}, {1, 1, 2}, {1.5, 2, 2}, {4, 1, 2}, {3.5, 2, 2}};

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
            double u = -centerProjection[2] / (points[i][2] - centerProjection[2]);
            points[i][0] = centerProjection[0] + (points[i][0] - centerProjection[0]) * u;
            points[i][1] = centerProjection[1] + (points[i][1] - centerProjection[1]) * u - 5;
            pointsWingsLeft[i][0] = centerProjection[0] + (pointsWingsLeft[i][0] - centerProjection[0]) * u;
            pointsWingsLeft[i][1] = centerProjection[1] + (pointsWingsLeft[i][1] - centerProjection[1]) * u - 5;
            pointsWingsRight[i][0] = centerProjection[0] + (pointsWingsRight[i][0] - centerProjection[0]) * u;
            pointsWingsRight[i][1] = centerProjection[1] + (pointsWingsRight[i][1] - centerProjection[1]) * u - 5;

            points[i][0] *= scale;
            points[i][1] *= scale;
            pointsWingsLeft[i][0] *= scale;
            pointsWingsLeft[i][1] *= scale;
            pointsWingsRight[i][0] *= scale;
            pointsWingsRight[i][1] *= scale;

            Graphics.putPixel((int) pointsWingsRight[i][0], (int) pointsWingsRight[i][1]);
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
            Graphics.putPixel((int) pointsWingsLeft[i][0], (int) pointsWingsLeft[i][1]);
        }

        // Body
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

        // Wings right
        Graphics.setColorRGB(255, 0, 0);
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[2][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[4][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[4][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[4][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[2][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[2][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[2][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[4][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[4][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[4][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[2][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[2][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        // Wings left
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[2][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[4][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[4][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[4][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[2][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[2][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[2][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[4][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[4][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[4][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[2][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[2][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});


        Graphics.setColorRGB(128, 128, 128); // Gray color
        for (int i = 0; i < points.length; i++) {
            points[i][0] += 300;
            points[i][1] -= 10;

            pointsWingsLeft[i][0] += 300;
            pointsWingsLeft[i][1] -= 10;
            pointsWingsRight[i][0] += 300;
            pointsWingsRight[i][1] -= 10;

            Graphics.putPixel((int) pointsWingsLeft[i][0], (int) pointsWingsLeft[i][1]);
            Graphics.putPixel((int) pointsWingsRight[i][0], (int) pointsWingsRight[i][1]);
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
        }

        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[2][0], (int) points[6][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[2][1], (int) points[6][1], (int) points[4][1]});
        Graphics.fillPolygon(new int[]{(int) points[1][0], (int) points[3][0], (int) points[7][0], (int) points[5][0]}, new int[]{(int) points[1][1], (int) points[3][1], (int) points[7][1], (int) points[5][1]});
        Graphics.fillPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
        Graphics.fillPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
        Graphics.drawPolygon(new int[]{(int) points[0][0], (int) points[2][0], (int) points[6][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[2][1], (int) points[6][1], (int) points[4][1]});
        Graphics.drawPolygon(new int[]{(int) points[1][0], (int) points[3][0], (int) points[7][0], (int) points[5][0]}, new int[]{(int) points[1][1], (int) points[3][1], (int) points[7][1], (int) points[5][1]});
        Graphics.drawPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]});
        Graphics.drawPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]});

        // Wings right
        Graphics.setColorRGB(255, 0, 0);
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[2][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[4][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[4][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[4][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeft[2][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[2][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[2][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[4][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[4][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[0][0], (int) pointsWingsLeft[1][0], (int) pointsWingsLeft[5][0], (int) pointsWingsLeft[4][0]},
                new int[]{(int) pointsWingsLeft[0][1], (int) pointsWingsLeft[1][1], (int) pointsWingsLeft[5][1], (int) pointsWingsLeft[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeft[2][0], (int) pointsWingsLeft[3][0], (int) pointsWingsLeft[7][0], (int) pointsWingsLeft[6][0]},
                new int[]{(int) pointsWingsLeft[2][1], (int) pointsWingsLeft[3][1], (int) pointsWingsLeft[7][1], (int) pointsWingsLeft[6][1]});
        // Wings left
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[2][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[4][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[4][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[4][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRight[2][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[2][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[2][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[4][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[4][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[0][0], (int) pointsWingsRight[1][0], (int) pointsWingsRight[5][0], (int) pointsWingsRight[4][0]},
                new int[]{(int) pointsWingsRight[0][1], (int) pointsWingsRight[1][1], (int) pointsWingsRight[5][1], (int) pointsWingsRight[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRight[2][0], (int) pointsWingsRight[3][0], (int) pointsWingsRight[7][0], (int) pointsWingsRight[6][0]},
                new int[]{(int) pointsWingsRight[2][1], (int) pointsWingsRight[3][1], (int) pointsWingsRight[7][1], (int) pointsWingsRight[6][1]});

        Graphics.setColorRGB(128, 128, 128); // Gray color
        for (int i = 0; i < points.length; i++) {
            points[i][0] -= 200;
            points[i][1] -= 200;

            pointsWingsLeft[i][0] -= 200;
            pointsWingsLeft[i][1] -= 200;
            pointsWingsRight[i][0] -= 200;
            pointsWingsRight[i][1] -= 200;


            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
            Graphics.putPixel((int) pointsWingsLeft[i][0], (int) pointsWingsLeft[i][1]);
            Graphics.putPixel((int) pointsWingsRight[i][0], (int) pointsWingsRight[i][1]);
        }

        double[][] rotatedPoints = Graphics.rotateCube(points, Math.toRadians(0), Math.toRadians(0), Math.toRadians(angle));

        if (angle > 360) {
            angle = 0;
        } else {
            angle++;
        }

        Graphics.fillPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[3][0], (int) rotatedPoints[2][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[3][1], (int) rotatedPoints[2][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[4][0], (int) rotatedPoints[5][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[4][1], (int) rotatedPoints[5][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[5][0], (int) rotatedPoints[4][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[5][1], (int) rotatedPoints[4][1]});
        Graphics.fillPolygon(new int[]{(int) rotatedPoints[2][0], (int) rotatedPoints[3][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[2][1], (int) rotatedPoints[3][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[3][0], (int) rotatedPoints[2][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[3][1], (int) rotatedPoints[2][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[4][0], (int) rotatedPoints[5][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[4][1], (int) rotatedPoints[5][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[0][0], (int) rotatedPoints[1][0], (int) rotatedPoints[5][0], (int) rotatedPoints[4][0]}, new int[]{(int) rotatedPoints[0][1], (int) rotatedPoints[1][1], (int) rotatedPoints[5][1], (int) rotatedPoints[4][1]});
        Graphics.drawPolygon(new int[]{(int) rotatedPoints[2][0], (int) rotatedPoints[3][0], (int) rotatedPoints[7][0], (int) rotatedPoints[6][0]}, new int[]{(int) rotatedPoints[2][1], (int) rotatedPoints[3][1], (int) rotatedPoints[7][1], (int) rotatedPoints[6][1]});

        double pointsWingsLeftRotated[][] = Graphics.rotateCube(pointsWingsLeft, Math.toRadians(0), Math.toRadians(0), Math.toRadians(angle));
        double pointsWingsRightRotated[][] = Graphics.rotateCube(pointsWingsRight, Math.toRadians(0), Math.toRadians(0), Math.toRadians(angle));
        // Wings right
        Graphics.setColorRGB(255, 0, 0);
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeftRotated[0][0], (int) pointsWingsLeftRotated[1][0], (int) pointsWingsLeftRotated[3][0], (int) pointsWingsLeftRotated[2][0]},
                new int[]{(int) pointsWingsLeftRotated[0][1], (int) pointsWingsLeftRotated[1][1], (int) pointsWingsLeftRotated[3][1], (int) pointsWingsLeftRotated[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeftRotated[4][0], (int) pointsWingsLeftRotated[5][0], (int) pointsWingsLeftRotated[7][0], (int) pointsWingsLeftRotated[6][0]},
                new int[]{(int) pointsWingsLeftRotated[4][1], (int) pointsWingsLeftRotated[5][1], (int) pointsWingsLeftRotated[7][1], (int) pointsWingsLeftRotated[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeftRotated[0][0], (int) pointsWingsLeftRotated[1][0], (int) pointsWingsLeftRotated[5][0], (int) pointsWingsLeftRotated[4][0]},
                new int[]{(int) pointsWingsLeftRotated[0][1], (int) pointsWingsLeftRotated[1][1], (int) pointsWingsLeftRotated[5][1], (int) pointsWingsLeftRotated[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsLeftRotated[2][0], (int) pointsWingsLeftRotated[3][0], (int) pointsWingsLeftRotated[7][0], (int) pointsWingsLeftRotated[6][0]},
                new int[]{(int) pointsWingsLeftRotated[2][1], (int) pointsWingsLeftRotated[3][1], (int) pointsWingsLeftRotated[7][1], (int) pointsWingsLeftRotated[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeftRotated[0][0], (int) pointsWingsLeftRotated[1][0], (int) pointsWingsLeftRotated[3][0], (int) pointsWingsLeftRotated[2][0]},
                new int[]{(int) pointsWingsLeftRotated[0][1], (int) pointsWingsLeftRotated[1][1], (int) pointsWingsLeftRotated[3][1], (int) pointsWingsLeftRotated[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeftRotated[4][0], (int) pointsWingsLeftRotated[5][0], (int) pointsWingsLeftRotated[7][0], (int) pointsWingsLeftRotated[6][0]},
                new int[]{(int) pointsWingsLeftRotated[4][1], (int) pointsWingsLeftRotated[5][1], (int) pointsWingsLeftRotated[7][1], (int) pointsWingsLeftRotated[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeftRotated[0][0], (int) pointsWingsLeftRotated[1][0], (int) pointsWingsLeftRotated[5][0], (int) pointsWingsLeftRotated[4][0]},
                new int[]{(int) pointsWingsLeftRotated[0][1], (int) pointsWingsLeftRotated[1][1], (int) pointsWingsLeftRotated[5][1], (int) pointsWingsLeftRotated[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsLeftRotated[2][0], (int) pointsWingsLeftRotated[3][0], (int) pointsWingsLeftRotated[7][0], (int) pointsWingsLeftRotated[6][0]},
                new int[]{(int) pointsWingsLeftRotated[2][1], (int) pointsWingsLeftRotated[3][1], (int) pointsWingsLeftRotated[7][1], (int) pointsWingsLeftRotated[6][1]});
        // Wings left
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillPolygon(new int[]{(int) pointsWingsRightRotated[0][0], (int) pointsWingsRightRotated[1][0], (int) pointsWingsRightRotated[3][0], (int) pointsWingsRightRotated[2][0]},
                new int[]{(int) pointsWingsRightRotated[0][1], (int) pointsWingsRightRotated[1][1], (int) pointsWingsRightRotated[3][1], (int) pointsWingsRightRotated[2][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRightRotated[4][0], (int) pointsWingsRightRotated[5][0], (int) pointsWingsRightRotated[7][0], (int) pointsWingsRightRotated[6][0]},
                new int[]{(int) pointsWingsRightRotated[4][1], (int) pointsWingsRightRotated[5][1], (int) pointsWingsRightRotated[7][1], (int) pointsWingsRightRotated[6][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRightRotated[0][0], (int) pointsWingsRightRotated[1][0], (int) pointsWingsRightRotated[5][0], (int) pointsWingsRightRotated[4][0]},
                new int[]{(int) pointsWingsRightRotated[0][1], (int) pointsWingsRightRotated[1][1], (int) pointsWingsRightRotated[5][1], (int) pointsWingsRightRotated[4][1]});
        Graphics.fillPolygon(new int[]{(int) pointsWingsRightRotated[2][0], (int) pointsWingsRightRotated[3][0], (int) pointsWingsRightRotated[7][0], (int) pointsWingsRightRotated[6][0]},
                new int[]{(int) pointsWingsRightRotated[2][1], (int) pointsWingsRightRotated[3][1], (int) pointsWingsRightRotated[7][1], (int) pointsWingsRightRotated[6][1]});
        Graphics.setColorRGB(0, 0, 0);
        Graphics.drawPolygon(new int[]{(int) pointsWingsRightRotated[0][0], (int) pointsWingsRightRotated[1][0], (int) pointsWingsRightRotated[3][0], (int) pointsWingsRightRotated[2][0]},
                new int[]{(int) pointsWingsRightRotated[0][1], (int) pointsWingsRightRotated[1][1], (int) pointsWingsRightRotated[3][1], (int) pointsWingsRightRotated[2][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRightRotated[4][0], (int) pointsWingsRightRotated[5][0], (int) pointsWingsRightRotated[7][0], (int) pointsWingsRightRotated[6][0]},
                new int[]{(int) pointsWingsRightRotated[4][1], (int) pointsWingsRightRotated[5][1], (int) pointsWingsRightRotated[7][1], (int) pointsWingsRightRotated[6][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRightRotated[0][0], (int) pointsWingsRightRotated[1][0], (int) pointsWingsRightRotated[5][0], (int) pointsWingsRightRotated[4][0]},
                new int[]{(int) pointsWingsRightRotated[0][1], (int) pointsWingsRightRotated[1][1], (int) pointsWingsRightRotated[5][1], (int) pointsWingsRightRotated[4][1]});
        Graphics.drawPolygon(new int[]{(int) pointsWingsRightRotated[2][0], (int) pointsWingsRightRotated[3][0], (int) pointsWingsRightRotated[7][0], (int) pointsWingsRightRotated[6][0]},
                new int[]{(int) pointsWingsRightRotated[2][1], (int) pointsWingsRightRotated[3][1], (int) pointsWingsRightRotated[7][1], (int) pointsWingsRightRotated[6][1]});

        if (centerProjection[0] > 5) {
            flag = false;
        } else if (centerProjection[0] <= 0.5) {
            flag = true;
        }

        if (flag) {
            centerProjection[0] += .02;
        } else {
            centerProjection[0] -= .02;
        }



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
        new Roblox().run();
    }
}
