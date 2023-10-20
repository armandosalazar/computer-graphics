package org.armando;

import static org.lwjgl.opengl.GL11.*;

public abstract class Graphics {
    public static void putPixel(int x, int y) {
        glBegin(GL_POINTS);
        glVertex2i(x, y);
        glEnd();
    }

    public static void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            putPixel(Math.round(x), Math.round(y));
            x += xInc;
            y += yInc;
        }
    }
}
