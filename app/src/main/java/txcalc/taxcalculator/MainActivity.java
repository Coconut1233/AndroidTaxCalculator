package txcalc.taxcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private static final String[] contracts = new String[]{"Dohoda o provedení práce",
                                                            "Dohoda o pracovní činnosti",
                                                            "Zkrácený úvazek",
                                                            "Plný úvazek"};
    private static boolean Student;
    private static boolean ZTP;
    private static String contract;
    private static boolean PKD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner droplist = findViewById(R.id.contracts);
        droplist.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, contracts));
        final CheckBox isStudent = findViewById(R.id.isStudent);
        final CheckBox isZTP = findViewById(R.id.isZTP);
        final CheckBox isPKD = findViewById(R.id.isPKD);
        Button toCalc = findViewById(R.id.toCalc);

        toCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contract = droplist.getSelectedItem().toString();
                if (isStudent.isChecked()) {
                    Student = true;
                }
                if (isZTP.isChecked()) {
                    ZTP = true;
                }
                if (isPKD.isChecked()) {
                    PKD = true;
                }
                goToCalc(v);
            }
        });
    }

    private void goToCalc(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public static String getContract() {
        return contract;
    }

    public static boolean isPKD() {
        return PKD;
    }

    public static String[] getContracts() {
        return contracts;

    }

    public static boolean isStudent() {
        return Student;
    }

    public static boolean isZTP() {
        return ZTP;
    }
}

