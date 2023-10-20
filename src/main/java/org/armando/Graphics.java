package org.armando;

import static org.lwjgl.opengl.GL11.*;

public abstract class Graphics {
    public static void putPixel(int x, int y) {
        glBegin(GL_POINTS);
        glVertex2i(x, y);
        glEnd();
    }
}
