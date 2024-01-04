package org.armandosalazar;

public class Scene {
    private final double[] centerProjection;
    private final double[] projectionBackground = {3, 2, 5};


    public Scene(double[] centerProjection) {
        this.centerProjection = centerProjection;
    }


    public void draw() {

        // {x, y, z} = 3d coordinates
        // double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};
        double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {-5, 1, 3}, {-4, 3, 3}, {9, 1, 3}, {8, 3, 3}};
        double[][] pointsGrandstands = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        Graphics.setColorRGB(255, 255, 255);

        int scale = 30;
        // Maths for projection: {x, y} = 2d coordinates
        for (int i = 0; i < points.length; i++) {
            double u = -projectionBackground[2] / (points[i][2] - projectionBackground[2]);
            points[i][0] = projectionBackground[0] + (points[i][0] - projectionBackground[0]) * u; // x
            points[i][1] = projectionBackground[1] + (points[i][1] - projectionBackground[1]) * u; // y

            points[i][0] *= scale;
            points[i][1] *= scale;
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
            Graphics.setColorRGB(0, 255, 255);
            Graphics.putPixel(0, 0);
            Graphics.setColorRGB(255, 255, 255);
        }

        for (int i = 0; i < pointsGrandstands.length; i++) {
            double u = -centerProjection[2] / (pointsGrandstands[i][2] - centerProjection[2]);
            pointsGrandstands[i][0] = centerProjection[0] + (pointsGrandstands[i][0] - centerProjection[0]) * u; // x
            pointsGrandstands[i][1] = centerProjection[1] + (pointsGrandstands[i][1] - centerProjection[1]) * u; // y

            pointsGrandstands[i][0] *= scale;
            pointsGrandstands[i][1] *= scale;
            Graphics.putPixel((int) pointsGrandstands[i][0], (int) pointsGrandstands[i][1]);
        }


        // Draw lines
        drawCubeLines(points);
        drawCubeLines(pointsGrandstands);

        Graphics.drawRectangle(-435, 135, 900, 100); // Draw eight windows inside the rectangle
        Graphics.fillRectangle(-424, 160, 100, 50);
        Graphics.fillRectangle(-313, 160, 100, 50);
        Graphics.fillRectangle(-202, 160, 100, 50);
        Graphics.fillRectangle(-91, 160, 100, 50);
        Graphics.fillRectangle(20, 160, 100, 50);
        Graphics.fillRectangle(131, 160, 100, 50);
        Graphics.fillRectangle(242, 160, 100, 50);
        Graphics.fillRectangle(353, 160, 100, 50);
        // TODO: Draw glass in the windows 90 x 40
        Graphics.setColorRGB(0, 0, 170);
        Graphics.fillRectangle(-419, 165, 90, 40);
        Graphics.fillRectangle(-308, 165, 90, 40);
        Graphics.fillRectangle(-197, 165, 90, 40);
        Graphics.fillRectangle(-86, 165, 90, 40);
        Graphics.fillRectangle(25, 165, 90, 40);
        Graphics.fillRectangle(136, 165, 90, 40);
        Graphics.fillRectangle(247, 165, 90, 40);
        Graphics.fillRectangle(358, 165, 90, 40);


        // Draw grades
        Graphics.setColorRGB(255, 0, 0);
        for (int i = 0; i < 25; i += 5) {
        }
    }

    private void drawCubeLines(double[][] points) {
        Graphics.drawLine((int) points[0][0], (int) points[0][1], (int) points[1][0], (int) points[1][1]);
        Graphics.drawLine((int) points[0][0], (int) points[0][1], (int) points[2][0], (int) points[2][1]);
        Graphics.drawLine((int) points[1][0], (int) points[1][1], (int) points[3][0], (int) points[3][1]);
        Graphics.drawLine((int) points[2][0], (int) points[2][1], (int) points[3][0], (int) points[3][1]);
        Graphics.drawLine((int) points[0][0], (int) points[0][1], (int) points[4][0], (int) points[4][1]);
        Graphics.drawLine((int) points[1][0], (int) points[1][1], (int) points[5][0], (int) points[5][1]);
        Graphics.drawLine((int) points[2][0], (int) points[2][1], (int) points[6][0], (int) points[6][1]);
        Graphics.drawLine((int) points[3][0], (int) points[3][1], (int) points[7][0], (int) points[7][1]);
        Graphics.drawLine((int) points[4][0], (int) points[4][1], (int) points[5][0], (int) points[5][1]);
        Graphics.drawLine((int) points[4][0], (int) points[4][1], (int) points[6][0], (int) points[6][1]);
        Graphics.drawLine((int) points[5][0], (int) points[5][1], (int) points[7][0], (int) points[7][1]);
        Graphics.drawLine((int) points[6][0], (int) points[6][1], (int) points[7][0], (int) points[7][1]);
    }

}
