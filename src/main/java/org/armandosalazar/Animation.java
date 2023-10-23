package org.armandosalazar;

public abstract class Animation {
    public static void bomberman(int x, int y) {
        // Manos
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x - 2, y + 14, 4, 4);
        Graphics.fillRect(x - 4, y + 12, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x - 6, y + 10, 4, 4);
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x + 10, y + 14, 4, 4);
        Graphics.fillRect(x + 12, y + 12, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x + 14, y + 10, 4, 4);
        // Traje
        Graphics.setColorRGB(100, 176, 255);
        Graphics.fillRect(x, y + 6, 12, 10);
        Graphics.fillRect(x + 2, y + 16, 8, 4);
        // Cabeza
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 20, 12, 12);
        Graphics.fillRect(x - 2, y + 22, 16, 8);
        // Ojos
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y + 24, 12, 4);
        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 2, y + 24, 2, 4);
        Graphics.fillRect(x + 8, y + 24, 2, 4);
        // Pans
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 4, 4, 4);
        Graphics.fillRect(x + 8, y + 4, 4, 4);
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y, 4, 4);
        Graphics.fillRect(x + 8, y, 4, 4);
    }

    public static void bombermanRightFoot(int x, int y) {
        // Manos
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x - 2, y + 14, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x - 6, y + 14, 4, 4);
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x + 10, y + 14, 4, 4);
        Graphics.fillRect(x + 12, y + 12, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x + 14, y + 10, 4, 4);
        // Traje
        Graphics.setColorRGB(100, 176, 255);
        Graphics.fillRect(x, y + 6, 12, 10);
        Graphics.fillRect(x + 2, y + 16, 8, 4);
        // Cabeza
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 20, 12, 12);
        Graphics.fillRect(x - 2, y + 22, 16, 8);
        // Ojos
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y + 24, 12, 4);
        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 2, y + 24, 2, 4);
        Graphics.fillRect(x + 8, y + 24, 2, 4);
        // Pans
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 4, 4, 6);
        Graphics.fillRect(x + 8, y + 6, 4, 2);
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y, 4, 4);
        Graphics.fillRect(x + 8, y + 2, 4, 4);
    }

    public static void bombermanLeftFoot(int x, int y) {
        // Manos
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x - 2, y + 14, 4, 4);
        Graphics.fillRect(x - 4, y + 12, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x - 6, y + 10, 4, 4);
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x + 10, y + 14, 4, 4);
        // Graphics.fillRect(x + 12, y + 12, 4, 4);
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x + 14, y + 14, 4, 4);
        // Traje
        Graphics.setColorRGB(100, 176, 255);
        Graphics.fillRect(x, y + 6, 12, 10);
        Graphics.fillRect(x + 2, y + 16, 8, 4);
        // Cabeza
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 20, 12, 12);
        Graphics.fillRect(x - 2, y + 22, 16, 8);
        // Ojos
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y + 24, 12, 4);
        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 2, y + 24, 2, 4);
        Graphics.fillRect(x + 8, y + 24, 2, 4);
        // Pans
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 4, 4, 4);
        Graphics.fillRect(x + 8, y + 4, 4, 6); // left foot
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y + 2, 4, 4);
        Graphics.fillRect(x + 8, y, 4, 4);
    }


    public static void concreteBlock(int x, int y) {
        Graphics.setColorRGB(176, 176, 176);
        Graphics.fillRect(x, y, 32, 32);
        Graphics.setColorRGB(0, 0, 0);
        Graphics.fillRect(x, y, 32, 2);
        Graphics.fillRect(x + 2, y + 2, 26, 2);
        Graphics.fillRect(x + 30, y, 2, 32);
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 2, 2, 28);
        Graphics.fillRect(x + 2, y + 30, 28, 2);
    }

    public static void brickBlock(int x, int y) {

    }
}
