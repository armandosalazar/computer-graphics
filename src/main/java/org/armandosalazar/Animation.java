package org.armandosalazar;

public abstract class Animation {
    public static void terrain(int[][] terrain, boolean state) {
        for (int i = 0, y = -208; i < terrain.length; i++, y += 32) {
            for (int j = 0, x = -496; j < terrain[i].length; j++, x += 32) {
                switch (terrain[i][j]) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        concreteBlock(x, y);
                        break;
                    }
                    case 2: {
                        brickBlock(x, y);
                        break;
                    }
                    case 3: {
                        enemy(x, y, state);
                        break;
                    }
                    case 4: {
                        bomb(x + 16, y + 16, state);
                        break;
                    }
                    case 5: {
                        fire(x, y);
                        break;
                    }
                }
            }
        }
    }
    public static void bomberman(int x, int y, int direction) {
        switch (direction) {
            case 1: {
                if (y % 10 == 0) {
                    bombermanLeftFootUp(x, y);
                } else {
                    bombermanRightFootUp(x, y);
                }
                break;
            }
            case 2: {
                if (y % 10 == 0) {
                    bombermanRightFoot(x, y);
                } else {
                    bombermanLeftFoot(x, y);
                }
                break;
            }
            case 3: {
                if (x % 10 == 0) {
                    bombermanToRight(x, y);
                } else {
                    bombermanToRightTwo(x, y);
                }
                break;
            }
            case 4: {
                if (x % 10 == 0) {
                    bombermanToLeft(x, y);
                } else {
                    bombermanToLeftTwo(x, y);
                }
                break;
            }
        }
    }

    public static void enemy(int x, int y, boolean state) {
        if (state) {
            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillRect(x + 2, y + 12, 28, 14);
            Graphics.fillRect(x + 4, y + 10, 24, 19);
            Graphics.fillRect(x + 6, y + 8, 20, 22);
            Graphics.fillRect(x + 8, y + 6, 16, 25);
            Graphics.fillRect(x + 12, y + 4, 8, 4);
            Graphics.fillRect(x + 14, y + 2, 4, 2);

            Graphics.setColorRGB(255, 129, 111);
            Graphics.fillRect(x + 4, y + 14, 24, 10);
            Graphics.fillRect(x + 6, y + 12, 20, 14);
            Graphics.fillRect(x + 8, y + 10, 16, 18);
            Graphics.fillRect(x + 10, y + 8, 12, 22);
            Graphics.fillRect(x + 12, y + 6, 8, 25);

            Graphics.setColorRGB(255, 255, 255);
            Graphics.fillRect(x + 10, y + 18, 4, 6);
            Graphics.fillRect(x + 18, y + 18, 4, 6);
            Graphics.fillRect(x + 14, y + 4, 4, 2);

            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillRect(x + 14, y + 10, 4, 2);
            Graphics.fillRect(x + 12, y + 18, 2, 4);
            Graphics.fillRect(x + 20, y + 18, 2, 4);
        } else {
            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillRect(x + 4, y + 12, 24, 14);
            Graphics.fillRect(x + 6, y + 10, 20, 19);
            Graphics.fillRect(x + 8, y + 8, 16, 22);
            Graphics.fillRect(x + 12, y + 6, 8, 24);
            Graphics.fillRect(x + 14, y + 2, 4, 2);

            Graphics.setColorRGB(255, 129, 111);
            Graphics.fillRect(x + 6, y + 14, 20, 10);
            Graphics.fillRect(x + 8, y + 12, 16, 14);
            Graphics.fillRect(x + 12, y + 10, 8, 18);

            Graphics.setColorRGB(255, 255, 255);
            Graphics.fillRect(x + 10, y + 16, 4, 6);
            Graphics.fillRect(x + 18, y + 16, 4, 6);

            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillRect(x + 12, y + 16, 2, 4);
            Graphics.fillRect(x + 20, y + 16, 2, 4);
        }
    }

    private static void bombermanStanding(int x, int y) {
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

    private static void bombermanRightFoot(int x, int y) {
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

    private static void bombermanLeftFoot(int x, int y) {
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

    private static void bombermanRightFootUp(int x, int y) {
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


    private static void bombermanLeftFootUp(int x, int y) {
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

    private static void bombermanToLeft(int x, int y) {
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

    private static void bombermanToLeftTwo(int x, int y) {
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


    private static void bombermanToRight(int x, int y) {
        Graphics.setColorRGB(100, 176, 255); // blue
        Graphics.fillRect(x + 4, y + 6, 12, 12);
        Graphics.fillRect(x + 6, y + 18, 12, 4);
        Graphics.fillRect(x + 12, y + 4, 4, 2);

        Graphics.setColorRGB(255, 255, 255); // white
        Graphics.fillRect(x + 4, y + 4, 6, 4); // left foot
        Graphics.fillRect(x + 2, y + 6, 2, 2);
        Graphics.fillRect(x + 8, y + 8, 2, 2);
        Graphics.fillRect(x + 8, y + 20, 12, 12); // head
        Graphics.fillRect(x + 6, y + 22, 16, 8);
        Graphics.fillRect(x + 4, y + 30, 2, 2); // hair
        Graphics.fillRect(x + 8, y + 14, 6, 4); // right hand
        Graphics.fillRect(x + 10, y + 12, 8, 4); // right hand
        Graphics.fillRect(x + 14, y + 6, 2, 2); // right foot
        Graphics.fillRect(x + 16, y + 4, 2, 6);

        Graphics.setColorRGB(255, 129, 111); // skin
        Graphics.fillRect(x + 2, y + 30, 2, 2);
        Graphics.fillRect(x, y + 12, 4, 4); // left hand
        Graphics.fillRect(x + 4, y, 4, 2); // left foot
        Graphics.fillRect(x + 2, y + 2, 4, 2);
        Graphics.fillRect(x, y + 4, 4, 2); // right foot
        Graphics.fillRect(x + 20, y + 10, 2, 2);
        Graphics.fillRect(x + 18, y + 4, 4, 6);
        Graphics.fillRect(x + 12, y + 24, 10, 4); // eyes
        Graphics.fillRect(x + 10, y + 26, 2, 2); // eyes
        Graphics.fillRect(x + 18, y + 14, 4, 4); // right hand
        Graphics.setColorRGB(56, 135, 0); // green
        Graphics.fillRect(x + 14, y + 24, 2, 4);
        Graphics.fillRect(x + 18, y + 24, 2, 4);
    }

    private static void bombermanToRightTwo(int x, int y) {
        Graphics.setColorRGB(100, 176, 255); // blue
        Graphics.fillRect(x + 6, y + 6, 12, 12);
        Graphics.fillRect(x + 18, y + 12, 2, 4);
        Graphics.fillRect(x + 8, y + 18, 4, 2);
        Graphics.fillRect(x + 6, y + 28, 2, 2);

        Graphics.setColorRGB(255, 255, 255); // white
        Graphics.fillRect(x + 10, y + 18, 12, 12);
        Graphics.fillRect(x + 8, y + 20, 16, 8);
        Graphics.fillRect(x + 4, y + 10, 4, 4); // left hand
        Graphics.fillRect(x + 6, y + 12, 4, 4);
        Graphics.fillRect(x + 8, y + 14, 4, 4);
        Graphics.fillRect(x + 2, y + 6, 4, 2);
        Graphics.fillRect(x + 4, y + 4, 4, 2);
        Graphics.fillRect(x + 10, y + 4, 6, 2);
        Graphics.fillRect(x + 12, y + 6, 4, 2);
        Graphics.fillRect(x + 18, y + 8, 2, 4);

        Graphics.setColorRGB(255, 129, 111); // skin
        Graphics.fillRect(x, y + 2, 2, 4); // left foot
        Graphics.fillRect(x + 2, y + 4, 2, 2);
        Graphics.fillRect(x + 2, y + 8, 4, 4); // left hand
        Graphics.fillRect(x + 10, y, 4, 4); // right foot
        Graphics.fillRect(x + 14, y, 2, 2);
        Graphics.fillRect(x + 20, y + 10, 4, 4);
        Graphics.fillRect(x + 14, y + 22, 10, 4);
        Graphics.fillRect(x + 12, y + 24, 2, 2);
        Graphics.fillRect(x + 4, y + 30, 2, 2);

        Graphics.setColorRGB(56, 135, 0); // green
        Graphics.fillRect(x + 16, y + 22, 2, 4);
        Graphics.fillRect(x + 20, y + 22, 2, 4);
        Graphics.fillRect(x + 16, y + 6, 2, 2);
    }

    public static void bomb(int x, int y, boolean normal) {
        if (normal) {
            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillCircle(x, y, 16);
            Graphics.setColorRGB(255, 255, 255);
            Graphics.fillRect(x - 12, y, 4, 4);
            Graphics.fillRect(x - 10, y + 4, 4, 4);
            Graphics.fillRect(x - 8, y + 8, 4, 2);
            Graphics.fillRect(x, y + 8, 2, 6);
            Graphics.fillRect(x + 2, y + 12, 2, 4);
            Graphics.fillRect(x + 4, y + 14, 4, 2);
            Graphics.fillRect(x + 10, y + 14, 2, 2);
            Graphics.fillRect(x + 14, y + 14, 2, 2);
            Graphics.fillRect(x + 12, y + 10, 2, 2);
        } else {
            Graphics.setColorRGB(0, 0, 0);
            Graphics.fillEllipse(x, y, 26, 32);
            Graphics.setColorRGB(255, 255, 255);
            Graphics.fillRect(x - 12, y, 4, 4);
            Graphics.fillRect(x - 10, y + 4, 4, 4);
            Graphics.fillRect(x - 8, y + 8, 4, 2);
            Graphics.fillRect(x, y + 8, 2, 6);
            Graphics.fillRect(x + 2, y + 12, 2, 4);
            Graphics.fillRect(x + 4, y + 14, 4, 2);
            Graphics.fillRect(x + 10, y + 14, 2, 2);
            Graphics.fillRect(x + 12, y + 10, 2, 2);
        }
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
        Graphics.setColorRGB(0, 0, 0);
        Graphics.fillRect(x, y, 32, 32);

        Graphics.setColorRGB(176, 176, 176);
        Graphics.fillRect(x, y + 2, 18, 8);
        Graphics.fillRect(x, y + 12, 10, 8);
        Graphics.fillRect(x + 2, y + 22, 28, 6);
        Graphics.fillRect(x + 14, y + 12, 18, 6);
        Graphics.fillRect(x + 22, y + 2, 10, 6);

        Graphics.setColorRGB(255, 255, 255);
        Graphics.fillRect(x, y + 8, 18, 2);
        Graphics.fillRect(x + 20, y + 2, 2, 6);
        Graphics.fillRect(x + 22, y + 8, 10, 2);
        Graphics.fillRect(x, y + 18, 10, 2);
        Graphics.fillRect(x, y + 22, 2, 6);
        Graphics.fillRect(x + 2, y + 28, 28, 2);
        Graphics.fillRect(x + 12, y + 12, 2, 6);
        Graphics.fillRect(x + 14, y + 18, 18, 2);
    }

    public static void fire(int x, int y) {
        Graphics.setColorRGB(181, 49, 33); // red
        Graphics.fillRect(x, y + 12, 32, 12);
        Graphics.fillRect(x + 2, y + 10, 26, 16);
        Graphics.fillRect(x + 4, y + 8, 24, 18);
        Graphics.fillRect(x + 6, y + 6, 20, 22);
        Graphics.fillRect(x + 8, y, 16, 32);

        Graphics.setColorRGB(255, 129, 111); // pink
        Graphics.fillRect(x, y + 14, 32, 8);
        Graphics.fillRect(x + 17, y, 6, 32);
        Graphics.fillRect(x + 12, y + 6, 8, 24);
        Graphics.fillRect(x + 10, y + 6, 12, 22);
        Graphics.fillRect(x + 6, y + 10, 18, 16);
        Graphics.fillRect(x + 8, y + 8, 20, 16);
        Graphics.fillRect(x + 12, y + 8, 8, 14);


        Graphics.setColorRGB(255, 204, 197); // white
        Graphics.fillRect(x + 6, y + 6, 2, 2);
        Graphics.fillRect(x + 8, y + 8, 2, 2);
        Graphics.fillRect(x + 10, y + 10, 2, 2);
        Graphics.fillRect(x + 8, y + 14, 2, 6);
        Graphics.fillRect(x + 6, y + 18, 2, 2);
        Graphics.fillRect(x + 12, y + 6, 2, 4);
        Graphics.fillRect(x + 16, y + 6, 4, 2);
        Graphics.fillRect(x + 18, y + 8, 2, 4);
        Graphics.fillRect(x + 20, y + 10, 2, 8);
        Graphics.fillRect(x + 22, y + 14, 2, 10);
        Graphics.fillRect(x + 24, y + 14, 2, 4);
        Graphics.fillRect(x + 26, y + 16, 2, 4);
        Graphics.fillRect(x + 8, y + 22, 12, 8);

        // green
        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x + 28, y + 24, 2, 2);

        // red
        Graphics.setColorRGB(181, 49, 33);
        Graphics.fillRect(x + 8, y + 10, 2, 4);
        Graphics.fillRect(x + 6, y + 14, 2, 4);
        Graphics.fillRect(x + 10, y + 12, 2, 6);
        Graphics.fillRect(x + 14, y + 18, 4, 6);
        Graphics.fillRect(x + 16, y + 24, 12, 2);
        Graphics.fillRect(x + 10, y + 22, 2, 6);
        Graphics.fillRect(x + 14, y + 6, 2, 8);
        Graphics.fillRect(x + 12, y + 10, 6, 2);
        Graphics.fillRect(x + 16, y + 14, 4, 2);
        Graphics.fillRect(x + 18, y + 12, 2, 6);
        Graphics.fillRect(x + 24, y + 16, 2, 6);
        Graphics.fillRect(x + 20, y + 8, 2, 2);
        Graphics.fillRect(x + 22, y + 12, 2, 2);
        // left fire
        Graphics.fillRect(x - 32, y + 12, 32, 12);
        Graphics.fillRect(x - 24, y + 10, 2, 2);
        Graphics.fillRect(x - 8, y + 10, 2, 2);
        Graphics.fillRect(x - 32, y + 24, 2, 2);
        Graphics.fillRect(x - 24, y + 24, 2, 2);
        Graphics.fillRect(x - 16, y + 24, 2, 2);
        Graphics.fillRect(x - 8, y + 24, 2, 2);
        // pink color
        Graphics.setColorRGB(255, 129, 111);
        Graphics.fillRect(x - 32, y + 16, 32, 4);
        Graphics.fillRect(x - 32, y + 14, 2, 2);
        Graphics.fillRect(x - 28, y + 14, 4, 2);
        Graphics.fillRect(x - 22, y + 14, 2, 2);
        Graphics.fillRect(x - 18, y + 14, 4, 2);
        Graphics.fillRect(x - 12, y + 14, 4, 2);
        Graphics.fillRect(x - 6, y + 14, 2, 2);
        Graphics.fillRect(x - 2, y + 14, 2, 2);
        // green
        Graphics.setColorRGB(56, 135, 0);
        Graphics.fillRect(x - 30, y + 12, 2, 2);
        Graphics.fillRect(x - 24, y + 12, 2, 2);
        Graphics.fillRect(x - 14, y + 12, 2, 2);
        Graphics.fillRect(x - 8, y + 12, 2, 2);

        Graphics.fillRect(x - 32, y + 22, 2, 2);
        Graphics.fillRect(x - 26, y + 22, 2, 2);
        Graphics.fillRect(x - 16, y + 22, 2, 2);
        Graphics.fillRect(x - 10, y + 22, 2, 2);

    }
}
