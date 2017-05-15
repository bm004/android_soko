package com.morcorp.sansoyunlarikuponolusturucu;

import java.io.Serializable;
import java.util.Random;

public class Loto implements Serializable {
    public int maxColumnNumber;
    public int maxRowNumber;
    public int randLimit;
    public int[][] kupon;
    public int rowNumber;
    public int sansFlag;
    public boolean uniqueCheck = false;
    public String gameType = "";
    public static final int SANS_LAST_RAND_LIMIT = 14;

    Loto(int maxColumnNumber, int maxRowNumber, int randLimit, int rowNumber, int sansFlag) {
        this.maxColumnNumber = maxColumnNumber;
        this.maxRowNumber = maxRowNumber;
        this.randLimit = randLimit;
        this.rowNumber = rowNumber;
        this.sansFlag = sansFlag;
    }

    public void kuponOlustur() {
        int n = 0, x = 0, y = 0, z = 0;
        kupon = new int[rowNumber][maxColumnNumber];

        Random rand = new Random();

        for (x = 0; x < rowNumber; x++) {
            for (y = 0; y < maxColumnNumber; y++) {
                if ((y == (maxColumnNumber - 1)) && (sansFlag == 1)) {
                    n = rand.nextInt(SANS_LAST_RAND_LIMIT) + 1;
                } else {
                    n = rand.nextInt(randLimit) + 1;
                }
                for (z = 0; z < y + 1; z++) {
                    if (n == kupon[x][z]) {
                        if ((z == (maxColumnNumber - 1)) && (sansFlag == 1)) {
                            break;
                        } else {
                            n = rand.nextInt(randLimit) + 1;
                        }
                        z = -1;
                    }
                }
                kupon[x][y] = n;
            }
        }
    }

    public void benzersizKuponOlustur() {
        int n = 0, x = 0, y = 0, z = 0, w = 0;
        kupon = new int[rowNumber][maxColumnNumber];

        Random rand = new Random();

        for (x = 0; x < rowNumber; x++) {
            for (y = 0; y < maxColumnNumber; y++) {
                if ((y == (maxColumnNumber - 1)) && (sansFlag == 1)) {
                    n = rand.nextInt(SANS_LAST_RAND_LIMIT) + 1;
                } else {
                    n = rand.nextInt(randLimit) + 1;
                }
                for (w = 0; w < rowNumber; w++) {
                    for (z = 0; z < maxColumnNumber; z++) {
                        if (0 == kupon[w][z])
                            break;
                        if ((y == (maxColumnNumber - 1)) && (sansFlag == 1)) {
                            if (n == kupon[w][y]) {
                                n = rand.nextInt(SANS_LAST_RAND_LIMIT) + 1;
                                w = -1;
                                z = 0;
                                break;
                            }
                        } else {
                            if (n == kupon[w][z]) {
                                n = rand.nextInt(randLimit) + 1;
                                w = -1;
                                z = 0;
                                break;
                            }
                        }
                    }
                }
                w = 0;
                z = 0;
                kupon[x][y] = n;
            }
        }

    }

    public void kuponSirala() {
        int x = 0, y = 0, z = 0, temp = 0;

        for (x = 0; x < rowNumber; x++) {
            for (y = 0; y < maxColumnNumber; y++)
                for (z = 1; z < (maxColumnNumber - y); z++) {

                    if ((z == (maxColumnNumber - 1)) && (sansFlag == 1)) {
                        continue;
                    } else {

                        if (kupon[x][z - 1] > kupon[x][z]) {
                            temp = kupon[x][z - 1];
                            kupon[x][z - 1] = kupon[x][z];
                            kupon[x][z] = temp;
                        }
                    }

                }
        }
    }

    public void kuponYazdir() {
        int x = 0, y = 0;

        for (x = 0; x < rowNumber; x++) {
            for (y = 0; y < maxColumnNumber; y++) {
                System.out.print(kupon[x][y] + " ");
            }
            System.out.println();
        }

    }

    public int getMaxColumnNumber() {
        return maxColumnNumber;
    }

    public void setMaxColumnNumber(int maxColumnNumber) {
        this.maxColumnNumber = maxColumnNumber;
    }

    public int getMaxRowNumber() {
        return maxRowNumber;
    }

    public void setMaxRowNumber(int maxRowNumber) {
        this.maxRowNumber = maxRowNumber;
    }

    public int getRandLimit() {
        return randLimit;
    }

    public void setRandLimit(int randLimit) {
        this.randLimit = randLimit;
    }

    public int[][] getKupon() {
        return kupon;
    }

    public void setKupon(int[][] kupon) {
        this.kupon = kupon;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public boolean getUniqueCheck() {
        return uniqueCheck;
    }

    public void setUniqueCheck(boolean uniqueCheck) {
        this.uniqueCheck = uniqueCheck;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

}
