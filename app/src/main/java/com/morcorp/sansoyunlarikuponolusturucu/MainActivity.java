package com.morcorp.sansoyunlarikuponolusturucu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_LOTO = "com.example.myfirstapp.LOTO";
    public static final String SAYISAL_LOTO = "Sayısal Loto";
    public static final String SUPER_LOTO = "Süper Loto";
    public static final String ON_NUMARA = "On Numara";
    public static final String SANS_TOPU = "Şans Topu";
    public static final int MAX_SAYISAL_ROW_NUMBER = 8;
    public static final int MAX_SUPER_ROW_NUMBER = 6;
    public static final int MAX_ONNUMARA_ROW_NUMBER = 5;
    public static final int MAX_SANSTOPU_ROW_NUMBER = 5;
    public static final int MAX_ONNUMARA_UNIQUE_ROW_NUMBER = 3;
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    Loto loto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addValuesToSpinners();
    }

    // Add values to Game Spinner and Column Spinner
    public void addValuesToSpinners() {
        final Spinner gamesSpinner = (Spinner) findViewById(R.id.games_spinner);
        // Create an ArrayAdapter using the string array and a custom spinner layout
        ArrayAdapter<CharSequence> gamesAdapter = ArrayAdapter.createFromResource(this,
                R.array.games_array, R.layout.spinner_layout);
        // Specify the layout to use when the list of choices appears
        gamesAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // Apply the adapter to the spinner
        gamesSpinner.setAdapter(gamesAdapter);

        Spinner columnSpinner = (Spinner) findViewById(R.id.column_spinner);
        List<String> list = new ArrayList<String>();

        gamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Spinner columnSpinner = (Spinner) findViewById(R.id.column_spinner);
                ArrayList<String> list = new ArrayList<String>();
                switch (parentView.getItemAtPosition(position).toString()) {
                    case SAYISAL_LOTO:
                        for (int i = 0; i < MAX_SAYISAL_ROW_NUMBER; i++)
                            list.add(Integer.toString(i + 1));
                        break;
                    case SUPER_LOTO:
                        for (int i = 0; i < MAX_SUPER_ROW_NUMBER; i++)
                            list.add(Integer.toString(i + 1));
                        break;
                    case ON_NUMARA:
                        for (int i = 0; i < MAX_ONNUMARA_ROW_NUMBER; i++)
                            list.add(Integer.toString(i + 1));
                        break;
                    case SANS_TOPU:
                        for (int i = 0; i < MAX_SANSTOPU_ROW_NUMBER; i++)
                            list.add(Integer.toString(i + 1));
                        break;

                }
                ArrayAdapter<String> columnAdapter = new ArrayAdapter<String>(MainActivity.this,
                        R.layout.spinner_layout, list);
                columnAdapter.setDropDownViewResource(R.layout.spinner_layout);
                columnSpinner.setAdapter(columnAdapter);

                columnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // ON NUMARA oyununda benzersiz kupon 3 den fazla kolon icin olusturulamaz
                        CheckBox uniqueCheck = (CheckBox) findViewById(R.id.unique_checkBox);
                        if (gamesSpinner.getSelectedItem().toString().equals(ON_NUMARA) && Integer.parseInt(parentView.getSelectedItem().toString()) > MAX_ONNUMARA_UNIQUE_ROW_NUMBER) {
                            uniqueCheck.setChecked(FALSE);
                            uniqueCheck.setEnabled(FALSE);
                            // Belirtilen durum icin bilgi mesajinin gosterimi
                            alertInfoMessage(getResources().getString(R.string.alert_message_onnumara));
                        } else {
                            uniqueCheck.setEnabled(TRUE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });
                // SANS TOPU oyununda kolonun son hanesi onceki hanelerle aynı sayı olabilir
                if (parentView.getSelectedItem().toString().equals(SANS_TOPU)) {
                    // Belirtilen durum icin bilgi mesajinin gosterimi
                    alertInfoMessage(getResources().getString(R.string.alert_message_sanstopu));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void alertInfoMessage(String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getResources().getString(R.string.alert_title))
                .setMessage(message)
                .setCancelable(TRUE)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }


    /**
     * Called when the user taps the Kupon Oluştur button
     */
    public void createCoupon(View view) {
        int rowNumber;
        Spinner gamesSpinner = (Spinner) findViewById(R.id.games_spinner);
        Spinner columnSpinner = (Spinner) findViewById(R.id.column_spinner);
        CheckBox uniqueCheck = (CheckBox) findViewById(R.id.unique_checkBox);

        rowNumber = Integer.parseInt(columnSpinner.getSelectedItem().toString());
        // Create loto object from the value of the gamesSpinner
        switch (gamesSpinner.getSelectedItem().toString()) {
            case SAYISAL_LOTO:
                loto = new Sayisal(rowNumber);
                loto.setGameType(SAYISAL_LOTO);
                break;
            case SUPER_LOTO:
                loto = new Superr(rowNumber);
                loto.setGameType(SUPER_LOTO);
                break;
            case ON_NUMARA:
                loto = new OnNumara(rowNumber);
                loto.setGameType(ON_NUMARA);
                break;
            case SANS_TOPU:
                loto = new SansTopu(rowNumber);
                loto.setGameType(SANS_TOPU);
                break;
        }
        // Set the uniqueCheckbox value
        loto.setUniqueCheck(uniqueCheck.isChecked());

        /*
         * Put serialized object(represented as a sequence of bytes) to intent
         * Start DisplayCouponActivity
         */
        Intent intent = new Intent(this, DisplayCouponActivity.class);
        intent.putExtra(EXTRA_LOTO, loto);
        startActivity(intent);
    }
}
