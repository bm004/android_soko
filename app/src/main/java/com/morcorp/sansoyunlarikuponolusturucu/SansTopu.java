package com.morcorp.sansoyunlarikuponolusturucu;

public class SansTopu extends Loto {
	public static final int MAX_COLUMN_NUMBER = 6;
	public static final int MAX_ROW_NUMBER = 5;
	public static final int RAND_LIMIT = 34;
	public static final int SANS_FLAG = 1;

	SansTopu(int rowNumber) {
		super(MAX_COLUMN_NUMBER, MAX_ROW_NUMBER, RAND_LIMIT, rowNumber, SANS_FLAG);
	}

}
