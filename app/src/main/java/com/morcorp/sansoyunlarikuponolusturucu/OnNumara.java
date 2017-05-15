package com.morcorp.sansoyunlarikuponolusturucu;

public class OnNumara extends Loto {
    public static final int MAX_COLUMN_NUMBER = 22;
    public static final int MAX_ROW_NUMBER = 5;
    public static final int RAND_LIMIT = 80;
    public static final int SANS_FLAG = 0;

    OnNumara(int rowNumber) {
        super(MAX_COLUMN_NUMBER, MAX_ROW_NUMBER, RAND_LIMIT, rowNumber, SANS_FLAG);
    }

}
