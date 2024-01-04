package org.armandosalazar;

public class Scene {
    private final double[] centerProjection;
    private final double[] projectionBackground = {3, 2, 5};
    private final int[] slate600 = {71, 85, 104};
    private final int[] slate700 = {51, 65, 85};
    private final int[] orange400 = {255, 191, 116};
    private final int[] cyan600 = {8, 145, 178};


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
        }

        for (int i = 0; i < pointsGrandstands.length; i++) {
            double u = -centerProjection[2] / (pointsGrandstands[i][2] - centerProjection[2]);
            pointsGrandstands[i][0] = centerProjection[0] + (pointsGrandstands[i][0] - centerProjection[0]) * u; // x
            pointsGrandstands[i][1] = centerProjection[1] + (pointsGrandstands[i][1] - centerProjection[1]) * u; // y

            pointsGrandstands[i][0] *= scale;
            pointsGrandstands[i][1] *= scale;
            Graphics.putPixel((int) pointsGrandstands[i][0], (int) pointsGrandstands[i][1]);
        }

        // Fill faces
        // Draw lines
        drawCubeLines(points);
        fillCubeFaces(points, false, true, false, false, false, false, new int[][]{{255, 255, 255}, {255, 255, 255}, {36, 66, 77}, {90, 0, 6}, {77, 57, 1}, {124, 4, 5}, {1, 24, 6}, {89, 1, 1}});

        drawCubeLines(pointsGrandstands);
        fillCubeFaces(pointsGrandstands, false, false, false, false, false, false, new int[][]{{255, 255, 0}, {0, 0, 0}, {36, 66, 77}, {90, 0, 6}, {77, 57, 1}, {124, 4, 5}, {1, 24, 6}, {89, 1, 1}});

        drawWindows();
    }


    private void drawWindows() {
        Graphics.setColorRBGbyArray(slate600);
        Graphics.fillRectangle(-435, 135, 900, 100); // Draw eight windows inside the rectangle
        Graphics.setColorRGB(0, 0, 0);
        Graphics.fillRectangle(-424, 160, 100, 50);
        Graphics.fillRectangle(-313, 160, 100, 50);
        Graphics.fillRectangle(-202, 160, 100, 50);
        Graphics.fillRectangle(-91, 160, 100, 50);
        Graphics.fillRectangle(20, 160, 100, 50);
        Graphics.fillRectangle(131, 160, 100, 50);
        Graphics.fillRectangle(242, 160, 100, 50);
        Graphics.fillRectangle(353, 160, 100, 50);
        // TODO: Draw glass in the windows 90 x 40
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRectangle(-419, 165, 90, 40);
        Graphics.fillRectangle(-308, 165, 90, 40);
        Graphics.fillRectangle(-197, 165, 90, 40);
        Graphics.fillRectangle(-86, 165, 90, 40);
        Graphics.fillRectangle(25, 165, 90, 40);
        Graphics.fillRectangle(136, 165, 90, 40);
        Graphics.fillRectangle(247, 165, 90, 40);
        Graphics.fillRectangle(358, 165, 90, 40);
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

    private void fillCubeFaces(double[][] points, boolean back, boolean front, boolean left, boolean right, boolean bottom, boolean top, int[][] colors) {
        Graphics.setColorRGB(colors[0][0], colors[0][1], colors[0][2]);
        if (back)
            Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]}); // Back
        Graphics.setColorRGB(colors[1][0], colors[1][1], colors[1][2]);
        if (front)
            Graphics.fillPolygon(new int[]{(int) points[4][0], (int) points[5][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[4][1], (int) points[5][1], (int) points[7][1], (int) points[6][1]}); // Front
        Graphics.setColorRGB(colors[2][0], colors[2][1], colors[2][2]);
        if (left)
            Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]}); // Left
        Graphics.setColorRGB(colors[3][0], colors[3][1], colors[3][2]);
        if (right)
            Graphics.fillPolygon(new int[]{(int) points[2][0], (int) points[3][0], (int) points[7][0], (int) points[6][0]}, new int[]{(int) points[2][1], (int) points[3][1], (int) points[7][1], (int) points[6][1]}); // Right
        Graphics.setColorRGB(colors[4][0], colors[4][1], colors[4][2]);
        if (bottom)
            Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[2][0], (int) points[6][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[2][1], (int) points[6][1], (int) points[4][1]}); // Bottom
        Graphics.setColorRGB(colors[5][0], colors[5][1], colors[5][2]);
        if (top)
            Graphics.fillPolygon(new int[]{(int) points[1][0], (int) points[3][0], (int) points[7][0], (int) points[5][0]}, new int[]{(int) points[1][1], (int) points[3][1], (int) points[7][1], (int) points[5][1]}); // Top
    }
}
