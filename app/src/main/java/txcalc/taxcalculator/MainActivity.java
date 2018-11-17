package txcalc.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String[] contracts = new String[]{"Dohoda o provedení práce",
                                                    "Dohoda o pracovní činnosti",
                                                    "Zkrácený úvazek",
                                                    "Plný úvazek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner droplist = findViewById(R.id.contracts);
        droplist.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, contracts));
        CheckBox isStudent  = findViewById(R.id.isStudent);
        CheckBox isZTP  = findViewById(R.id.isZTP);
        Button toCalc = findViewById(R.id.toCalc);

        toCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.second_activity);
            }
        });
        Button calc = findViewById(R.id.calc);
        final TextView result = findViewById(R.id.result);
        final EditText salaryInput = findViewById(R.id.SalaryInput);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double salary = Double.parseDouble(salaryInput.getText().toString());
                result.setText(String.valueOf(calculateTax(salary)));
            }
        });

    }
    private static double calculateTax(double salary){
        double tax = 0;
        if(salary>10000){
            tax = 0.1*salary;
        }
        return tax;
    }
}
