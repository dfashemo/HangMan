package org.cis1200.hangman;

import java.awt.*;

public class Body extends Hangman {

    private final int rows = 6;
    private final int columns = 6;
    private String[][] picture = new String[rows][columns];

    public boolean isArrayNonEmpty(int a, int b) {
        return (picture[a][b] != null);
    }

    private void printArray(Graphics g) {
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] != null) {
                    g.drawString(picture[i][j], 250 + j * 20, 200 + i * 20);
                }
            }
        }
    }

    public void drawStandArray() {
        for (int n = 1; n < rows; n++) {
            picture[n][0] = "||";
        }
        for (int r = 0; r < columns; r++) {
            picture[0][r] = "=";
        }
        picture[1][3] = "|";
    }

    public void drawHeadArray() {
        picture[2][3] = "O";
    }

    public void drawBodyArray() {
        picture[3][3] = "|";
    }

    public void drawRightArmArray() {
        picture[3][2] = "--";
    }

    public void drawLeftArmArray() {
        picture[3][4] = "--";
    }

    public void drawRightLegArray() {
        picture[4][4] = "\\";
    }

    public void drawLeftLegArray() {
        picture[4][2] = "/";
    }

    public void resetPicture() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                picture[i][j] = null;
            }
        }
    }

    public void drawStand(Graphics g) {
        resetPicture();
        drawStandArray();
        printArray(g);

    }

    public void drawHead(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        printArray(g);
    }

    public void drawBody(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        drawBodyArray();
        printArray(g);
    }

    public void drawLeftLeg(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        drawBodyArray();
        drawLeftLegArray();
        printArray(g);
    }

    public void drawRightLeg(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        drawBodyArray();
        drawLeftLegArray();
        drawRightLegArray();
        printArray(g);
    }

    public void drawLeftArm(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        drawBodyArray();
        drawLeftLegArray();
        drawRightLegArray();
        drawLeftArmArray();
        printArray(g);
    }

    public void drawRightArm(Graphics g) {
        resetPicture();
        drawStandArray();
        drawHeadArray();
        drawBodyArray();
        drawLeftLegArray();
        drawRightLegArray();
        drawLeftArmArray();
        drawRightArmArray();
        printArray(g);
    }
}
