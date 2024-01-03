package org.armandosalazar;

public class ElBrayan {

    private static double[] centerProjection;
    private static double[] secondCenterProjection = {-4.0, 4.0, 13.0};

    public static void draw(double[] centerProjection) {
        ElBrayan.centerProjection = centerProjection;

        double[][] points = {{1, 1, 1}, {1, 3, 1}, {3, 1, 1}, {3, 3, 1}, {1, 1, 3}, {1, 3, 3}, {3, 1, 3}, {3, 3, 3}};

        Graphics.setColorRGB(255, 255, 255);

        int scale = 30;
        // Maths for projection
        for (int i = 0; i < points.length; i++) {
            double u = -ElBrayan.centerProjection[2] / (points[i][2] - ElBrayan.centerProjection[2]);
            points[i][0] = ElBrayan.centerProjection[0] + (points[i][0] - ElBrayan.centerProjection[0]) * u;
            points[i][1] = ElBrayan.centerProjection[1] + (points[i][1] - ElBrayan.centerProjection[1]) * u - 5;

            points[i][0] *= scale;
            points[i][1] *= scale;
            Graphics.putPixel((int) points[i][0], (int) points[i][1]);
        }

        // Draw lines
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

        // Fill the cube
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[3][0], (int) points[2][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[3][1], (int) points[2][1]});
        Graphics.setColorRGB(189, 120, 0);
        Graphics.fillPolygon(new int[]{(int) points[0][0], (int) points[1][0], (int) points[5][0], (int) points[4][0]}, new int[]{(int) points[0][1], (int) points[1][1], (int) points[5][1], (int) points[4][1]});
    }
}
