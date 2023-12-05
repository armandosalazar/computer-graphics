package org.armandosalazar;

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

    public static void drawCircle(int x, int y, int radius) {
        int x1 = 0;
        int y1 = radius;
        int p = 1 - radius;

        while (x1 <= y1) {
            putPixel(x + x1, y + y1);
            putPixel(x + x1, y - y1);
            putPixel(x - x1, y + y1);
            putPixel(x - x1, y - y1);
            putPixel(x + y1, y + x1);
            putPixel(x + y1, y - x1);
            putPixel(x - y1, y + x1);
            putPixel(x - y1, y - x1);

            if (p < 0) {
                p += 2 * x1 + 3;
            } else {
                p += 2 * (x1 - y1) + 5;
                y1--;
            }
            x1++;
        }
    }

    public static void drawRectangle(int x, int y, int width, int height) {
        drawLine(x, y, x + width, y);
        drawLine(x + width, y, x + width, y + height);
        drawLine(x + width, y + height, x, y + height);
        drawLine(x, y + height, x, y);
    }

    public static void drawEllipse(int x, int y, int width, int height) {
        int a = width / 2;
        int b = height / 2;
        int x1 = 0;
        int y1 = b;
        int p = (int) Math.round(Math.pow(b, 2) - Math.pow(a, 2) * b + Math.pow(a, 2) / 4);

        while (2 * Math.pow(b, 2) * x1 < 2 * Math.pow(a, 2) * y1) {
            putPixel(x + x1, y + y1);
            putPixel(x + x1, y - y1);
            putPixel(x - x1, y + y1);
            putPixel(x - x1, y - y1);

            if (p < 0) {
                p += (int) (2 * Math.pow(b, 2) * x1 + 3 * Math.pow(b, 2));
            } else {
                p += (int) (2 * Math.pow(b, 2) * x1 - 2 * Math.pow(a, 2) * y1 + 2 * Math.pow(a, 2) + 3 * Math.pow(b, 2));
                y1--;
            }
            x1++;
        }

        p = (int) Math.round(Math.pow(b, 2) * Math.pow(x1, 2) + Math.pow(a, 2) * Math.pow(y1 - 1, 2) - Math.pow(a, 2) * Math.pow(b, 2));

        while (y1 >= 0) {
            putPixel(x + x1, y + y1);
            putPixel(x + x1, y - y1);
            putPixel(x - x1, y + y1);
            putPixel(x - x1, y - y1);

            if (p > 0) {
                p += (int) (-2 * Math.pow(a, 2) * y1 + 3 * Math.pow(a, 2));
            } else {
                p += (int) (2 * Math.pow(b, 2) * x1 - 2 * Math.pow(a, 2) * y1 + 2 * Math.pow(b, 2) + 3 * Math.pow(a, 2));
                x1++;
            }
            y1--;
        }

    }

    public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        drawLine(x1, y1, x2, y2);
        drawLine(x2, y2, x3, y3);
        drawLine(x3, y3, x1, y1);
    }

    public static void drawPolygon(int[] x, int[] y) {
        for (int i = 0; i < x.length - 1; i++) {
            drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }
        drawLine(x[x.length - 1], y[y.length - 1], x[0], y[0]);
    }

    public static void fillRect(int x, int y, int width, int height) {
        for (int i = 0; i <= height; i++) {
            drawLine(x, y + i, x + width, y + i);
        }
    }

    public static void fillCircle(int x, int y, int radius) {
        int x1 = 0;
        int y1 = radius;
        int p = 1 - radius;

        while (x1 <= y1) {
            drawLine(x + x1, y + y1, x + x1, y - y1);
            drawLine(x - x1, y + y1, x - x1, y - y1);
            drawLine(x + y1, y + x1, x + y1, y - x1);
            drawLine(x - y1, y + x1, x - y1, y - x1);

            if (p < 0) {
                p += 2 * x1 + 3;
            } else {
                p += 2 * (x1 - y1) + 5;
                y1--;
            }
            x1++;
        }
    }

    public static void fillEllipse(int x, int y, int width, int height) {
        int a = width / 2;
        int b = height / 2;
        int x1 = 0;
        int y1 = b;
        int p = (int) Math.round(Math.pow(b, 2) - Math.pow(a, 2) * b + Math.pow(a, 2) / 4);

        while (2 * Math.pow(b, 2) * x1 < 2 * Math.pow(a, 2) * y1) {
            drawLine(x + x1, y + y1, x + x1, y - y1);
            drawLine(x - x1, y + y1, x - x1, y - y1);

            if (p < 0) {
                p += (int) (2 * Math.pow(b, 2) * x1 + 3 * Math.pow(b, 2));
            } else {
                p += (int) (2 * Math.pow(b, 2) * x1 - 2 * Math.pow(a, 2) * y1 + 2 * Math.pow(a, 2) + 3 * Math.pow(b, 2));
                y1--;
            }
            x1++;
        }

        p = (int) Math.round(Math.pow(b, 2) * Math.pow(x1, 2) + Math.pow(a, 2) * Math.pow(y1 - 1, 2) - Math.pow(a, 2) * Math.pow(b, 2));

        while (y1 >= 0) {
            drawLine(x + x1, y + y1, x + x1, y - y1);
            drawLine(x - x1, y + y1, x - x1, y - y1);

            if (p > 0) {
                p += (int) (-2 * Math.pow(a, 2) * y1 + 3 * Math.pow(a, 2));
            } else {
                p += (int) (2 * Math.pow(b, 2) * x1 - 2 * Math.pow(a, 2) * y1 + 2 * Math.pow(b, 2) + 3 * Math.pow(a, 2));
                x1++;
            }
            y1--;
        }

    }


    public static void fillPolygon(int[] x, int[] y) {
        glBegin(GL_POLYGON);
        for (int i = 0; i < x.length; i++) {
            glVertex2i(x[i], y[i]);
        }
        glEnd();
    }

    public static double[][] rotateCube(double[][] points, double angleX, double angleY, double angleZ) {
        // Crear matrices de rotación
        double[][] rotationX = {
                {1, 0, 0},
                {0, Math.cos(angleX), -Math.sin(angleX)},
                {0, Math.sin(angleX), Math.cos(angleX)}
        };
        double[][] rotationY = {
                {Math.cos(angleY), 0, Math.sin(angleY)},
                {0, 1, 0},
                {-Math.sin(angleY), 0, Math.cos(angleY)}
        };
        double[][] rotationZ = {{Math.cos(angleZ), -Math.sin(angleZ), 0}, {Math.sin(angleZ), Math.cos(angleZ), 0}, {0, 0, 1}};

        // Rotar puntos
        double[][] rotatedPoints = new double[points.length][3];
        for (int i = 0; i < points.length; i++) {
            // Rotar alrededor del eje x
            for (int j = 0; j < 3; j++) {
                rotatedPoints[i][j] = points[i][0] * rotationX[j][0] + points[i][1] * rotationX[j][1] + points[i][2] * rotationX[j][2];
            }
            // Rotar alrededor del eje y
            double[] temp = rotatedPoints[i].clone();
            for (int j = 0; j < 3; j++) {
                rotatedPoints[i][j] = temp[0] * rotationY[j][0] + temp[1] * rotationY[j][1] + temp[2] * rotationY[j][2];
            }
            // Rotar alrededor del eje z
            temp = rotatedPoints[i].clone();
            for (int j = 0; j < 3; j++) {
                rotatedPoints[i][j] = temp[0] * rotationZ[j][0] + temp[1] * rotationZ[j][1] + temp[2] * rotationZ[j][2];
            }
        }

        return rotatedPoints;
    }

    public static void setColorRGB(int r, int g, int b) {
        glColor3f(r / 255.0f, g / 255.0f, b / 255.0f);
    }

}
