package com.morcorp.sansoyunlarikuponolusturucu;

public class Superr extends Loto {
	public static final int MAX_COLUMN_NUMBER = 6;
	public static final int MAX_ROW_NUMBER = 6;
	public static final int RAND_LIMIT = 54;
	public static final int SANS_FLAG = 0;

	Superr(int rowNumber) {
		super(MAX_COLUMN_NUMBER, MAX_ROW_NUMBER, RAND_LIMIT, rowNumber, SANS_FLAG);
	}

}
