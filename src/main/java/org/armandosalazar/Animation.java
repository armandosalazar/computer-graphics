package org.armandosalazar;

public abstract class Animation {
    public static void bomberman(int x, int y) {
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y, 4, 4);
        Graphics.fillRect(x + 8, y, 4, 4);
        // Manos
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x - 2, y + 14, 4, 4);
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
    }
}
