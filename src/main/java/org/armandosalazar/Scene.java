package org.armandosalazar;

public class Scene {
    private final double[] centerProjection;
    private final double[] projectionBackground = {3, 2, 5};
    private final double[] projectionGrandstandsCenter = {2, 2, 5};

    private final int[] slate100 = {241, 245, 249};
    private final int[] slate600 = {71, 85, 104};
    private final int[] slate700 = {51, 65, 85};
    private final int[] slate800 = {30, 41, 59};
    private final int[] slate900 = {2, 6, 23};

    private final int[] orange400 = {251, 146, 60};
    private final int[] orange600 = {234, 88, 12};

    public Scene(double[] centerProjection) {
        this.centerProjection = centerProjection;
    }


    public void draw() {
        // {x, y, z} = 3d coordinates
        // double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};
        double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {-5, 1, 3}, {-4, 3, 3}, {9, 1, 3}, {8, 3, 3}};
        double[][] pointsGrandstandsCenter = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        Graphics.setColorRGB(255, 255, 255);

        int scale = 30;
        // Maths for projection: {x, y} = 2d coordinates
        mathsForProjection(points, projectionBackground, scale);
        mathsForProjection(pointsGrandstandsCenter, projectionGrandstandsCenter, scale);

        // TODO: Draw background
        Graphics.setColorRBGbyArray(slate600);
        Graphics.fillRectangle(-500, 0, 1000, 600); // Draw rectangle

        // Draw lines
        fillCubeFaces(points, false, true, false, false, false, false, new int[][]{{255, 255, 255}, slate700, {36, 66, 77}, {90, 0, 6}, {77, 57, 1}, {124, 4, 5}, {1, 24, 6}, {89, 1, 1}});

        Graphics.setColorRBGbyArray(slate800);
        Graphics.fillRectangle(-500, -15, 1000, 20); // Draw rectangle
        Graphics.fillRectangle(-490, 25, 1000, 20); // Draw rectangle
        Graphics.fillRectangle(-470, 65, 970, 20); // Draw rectangle
        Graphics.fillRectangle(-450, 105, 930, 20); // Draw rectangle

        fillCubeFaces(pointsGrandstandsCenter, true, false, true, true, true, true, new int[][]{slate600, orange400, slate700, slate700, slate800, slate800});
        drawCubeLines(pointsGrandstandsCenter);

        // Draw court with polygon
        Graphics.setColorRBGbyArray(slate100);
        Graphics.fillPolygon(new int[]{-500, -300, 500, 500}, new int[]{-300, -15, -15, -300});
        Graphics.setColorRBGbyArray(orange400);
        Graphics.fillPolygon(new int[]{-480, -285, 500, 500}, new int[]{-300, -30, -30, -300});
        // TODO: Draw lines in the court
        Graphics.setColorRBGbyArray(slate100);
        Graphics.fillCircle(100, -157, 60);
        Graphics.setColorRBGbyArray(orange400);
        Graphics.fillCircle(100, -157, 50);
        Graphics.setColorRBGbyArray(slate100);
        Graphics.fillRectangle(90, -300, 15, 280);

        drawWindows();

        // TODO: Draw baskets
        Graphics.setColorRBGbyArray(slate600);
        Graphics.fillRectangle(-395, -150, 25, 200); // Draw rectangle
        Graphics.setColorRBGbyArray(slate100);
        Graphics.fillRectangle(-435, 10, 100, 80); // Draw rectangle
        Graphics.setColorRBGbyArray(orange400);
        Graphics.fillRectangle(-425, 20, 80, 60); // Draw rectangle
        Graphics.setColorRBGbyArray(slate100);
        Graphics.fillRectangle(-410, 40, 50, 20); // Draw rectangle


    }

    private void mathsForProjection(double[][] points, double[] projectionVector, int scale) {
        for (int i = 0; i < points.length; i++) {
            double u = -projectionVector[2] / (points[i][2] - projectionVector[2]);
            points[i][0] = projectionVector[0] + (points[i][0] - projectionVector[0]) * u; // x
            points[i][1] = projectionVector[1] + (points[i][1] - projectionVector[1]) * u; // y

            points[i][0] *= scale;
            points[i][1] *= scale;
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
        }
    }


    private void drawWindows() {
        Graphics.setColorRBGbyArray(slate900);
        Graphics.fillRectangle(-435, 135, 900, 100); // Draw eight windows inside the rectangle
        Graphics.setColorRBGbyArray(slate800);
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
