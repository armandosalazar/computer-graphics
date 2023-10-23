package org.armandosalazar;

public abstract class Animation {
    public static void bomberman(int x, int y, int direction) {
        switch (direction) {
            case 1: {
                if (y % 20 == 0) {
                    bombermanLeftFootUp(x, y);
                } else if (y % 10 == 0) {
                    bombermanRightFootUp(x, y);
                } else {
                    bombermanStanding(x, y);
                }
                break;
            }
            case 2: {
                if (y % 20 == 0) {
                    bombermanRightFoot(x, y);
                } else if (y % 10 == 0) {
                    bombermanLeftFoot(x, y);
                } else {
                    bombermanStanding(x, y);
                }
                break;
            }
            case 4: {
                if (x % 20 == 0) {
                    bombermanToLeft(x, y);
                } else if (x % 10 == 0) {
                    bombermanToLeftTwo(x, y);
                } else {
                    bombermanStanding(x, y);
                }
                break;
            }
        }
    }

    public static void bombermanStanding(int x, int y) {
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

    public static void bombermanRightFootUp(int x, int y) {
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
        Graphics.fillRect(x + 4, y + 12, 4, 2);
        // Cabeza
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 20, 12, 12);
        Graphics.fillRect(x - 2, y + 22, 16, 8);
        Graphics.setColorRGB(100, 176, 255); // hair
        Graphics.fillRect(x + 4, y + 28, 4, 2);
        Graphics.fillRect(x + 8, y + 26, 2, 2);
        // Pans
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 4, 4, 6);
        Graphics.fillRect(x + 8, y + 6, 4, 2);
        Graphics.fillRect(x, y + 10, 12, 2);
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y, 4, 4);
        Graphics.fillRect(x + 8, y + 2, 4, 4);
        Graphics.fillRect(x + 4, y + 12, 4, 2);
    }


    public static void bombermanLeftFootUp(int x, int y) {
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
        Graphics.setColorRGB(100, 176, 255); // hair
        Graphics.fillRect(x + 4, y + 28, 4, 2);
        Graphics.fillRect(x + 8, y + 26, 2, 2);
        // Pans
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 4, 4, 4);
        Graphics.fillRect(x + 8, y + 4, 4, 6); // left foot
        Graphics.fillRect(x, y + 10, 12, 2);
        // Pies
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x, y + 2, 4, 4);
        Graphics.fillRect(x + 8, y, 4, 4);
        Graphics.fillRect(x + 4, y + 12, 4, 2);
    }

    public static void bombermanToLeft(int x, int y) {
        // body blue
        Graphics.setColorRGB(100, 176, 255);
        Graphics.fillRect(x + 6, y + 4, 4, 16);
        Graphics.fillRect(x + 10, y + 6, 6, 16);
        Graphics.fillRect(x + 16, y + 8, 2, 10);
        // white
        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x + 4, y + 4, 2, 6);
        Graphics.fillRect(x + 6, y + 6, 2, 2);
        Graphics.fillRect(x + 4, y + 12, 8, 4);
        Graphics.fillRect(x + 8, y + 14, 6, 4);
        Graphics.fillRect(x + 2, y + 20, 12, 12);
        Graphics.fillRect(x, y + 22, 16, 8);
        Graphics.fillRect(x + 12, y + 4, 8, 4);
        Graphics.fillRect(x + 12, y + 8, 2, 2);
        Graphics.fillRect(x + 17, y + 30, 2, 2);

        Graphics.setColorRGB(255, 129, 111); // skin color
        Graphics.fillRect(x, y + 4, 4, 6);
        Graphics.fillRect(x, y + 10, 2, 2);
        Graphics.fillRect(x, y + 14, 4, 4);
        Graphics.fillRect(x + 14, y, 4, 2);
        Graphics.fillRect(x + 16, y + 2, 4, 2);
        Graphics.fillRect(x + 18, y + 4, 4, 2);
        Graphics.fillRect(x + 20, y + 30, 2, 2);

        Graphics.fillRect(x + 18, y + 12, 4, 4); // right hand
        Graphics.fillRect(x, y + 24, 10, 4); // eyes
        Graphics.fillRect(x + 10, y + 26, 2, 2);

        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 2, y + 24, 2, 4);
        Graphics.fillRect(x + 8, y + 24, 2, 4);
    }

    public static void bombermanToLeftTwo(int x, int y) {
        Graphics.setColorRGB(100, 176, 255); // body blue
        Graphics.fillRect(x + 6, y + 8, 6, 10);
        Graphics.fillRect(x + 12, y + 6, 4, 14);
        Graphics.fillRect(x + 16, y + 6, 2, 12);
        Graphics.fillRect(x + 4, y + 12, 2, 4);
        Graphics.fillRect(x + 16, y + 28, 2, 2);

        Graphics.setColorRGB(255, 255, 255); // white
        Graphics.fillRect(x + 2, y + 18, 12, 12);
        Graphics.fillRect(x, y + 20, 16, 8);
        Graphics.fillRect(x + 16, y + 10, 4, 4); // right hand
        Graphics.fillRect(x + 14, y + 12, 4, 4);
        Graphics.fillRect(x + 12, y + 14, 4, 4);
        Graphics.fillRect(x + 4, y + 8, 2, 4); // left foot
        Graphics.fillRect(x + 8, y + 4, 4, 4);
        Graphics.fillRect(x + 12, y + 4, 2, 2);
        Graphics.fillRect(x + 16, y + 4, 4, 2); // right foot
        Graphics.fillRect(x + 18, y + 6, 4, 2);

        Graphics.setColorRGB(255, 129, 111); // skin color
        Graphics.fillRect(x, y + 12, 4, 4); // left hand
        Graphics.fillRect(x, y + 22, 10, 4); // eyes
        Graphics.fillRect(x + 10, y + 24, 2, 2);
        Graphics.fillRect(x + 8, y, 6, 2);
        Graphics.fillRect(x + 10, y + 2, 4, 2);
        Graphics.fillRect(x + 18, y + 8, 4, 4);
        Graphics.fillRect(x + 20, y + 4, 4, 2);
        Graphics.fillRect(x + 22, y + 2, 2, 2);
        Graphics.fillRect(x + 18, y + 30, 2, 2);

        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 2, y + 22, 2, 4);
        Graphics.fillRect(x + 8, y + 22, 2, 4);


    }


    public static void bombermanToRight(int x, int y) {
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
