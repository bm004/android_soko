package com.morcorp.sansoyunlarikuponolusturucu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DisplayCouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_coupon);

        // Get the Intent that started this activity and extract the object
        Intent intent = getIntent();
        Loto loto = (Loto) intent.getSerializableExtra(MainActivity.EXTRA_LOTO);

        // Create local kupon
        int[][] kupon = new int[loto.getRowNumber()][loto.getMaxColumnNumber()];
        if (loto.getUniqueCheck()) {
            loto.benzersizKuponOlustur();
        } else {
            loto.kuponOlustur();
        }

        loto.kuponSirala();

        kupon = loto.getKupon();

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.append(loto.getGameType() + " kuponunuzun" + "\n\n");
        int currentRow = 0;
        for (int x = 0; x < loto.getRowNumber(); x++) {
            currentRow = x + 1;
            textView.append("" + currentRow + ". kolonu:\n");
            for (int y = 0; y < loto.getMaxColumnNumber(); y++) {
                // Tek haneli sayilarin onune 0 konulmasi
                if (kupon[x][y] < 10)
                    textView.append("0");
                // textView.append(String.valueOf(kupon[x][y]));
                textView.append("" + kupon[x][y] + " ");
                // Sans topunda her kolon icin son sayıdan once + yazılması
                if ((loto.getGameType().equals(MainActivity.SANS_TOPU)) && (y == (loto.maxColumnNumber - 2)))
                    textView.append("+ ");
            }
            textView.append("\n\n");
        }
        //Making textView scrollable
        textView.setMovementMethod(new ScrollingMovementMethod());
        // TODO Kupon Kaydet!
    }
}
