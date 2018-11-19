package txcalc.taxcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
    final String contract = MainActivity.getContract();
    final boolean isStudent = MainActivity.isStudent();
    final boolean isZTP = MainActivity.isZTP();
    final String[] contracts = MainActivity.getContracts();
    final boolean isPKD = MainActivity.isPKD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Button calc = findViewById(R.id.calc);
        final TextView tax = findViewById(R.id.tax);
        final TextView cleanInocme = findViewById(R.id.cleanIncome);
        final EditText salaryInput = findViewById(R.id.SalaryInput);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double salary = Double.parseDouble(salaryInput.getText().toString());
                try {
                    double cTax = calculateTax(salary);
                    tax.setText(String.valueOf(cTax));
                    cleanInocme.setText(String.valueOf(salary-cTax));
                } catch (NumberFormatException e) {
                    tax.setText(R.string.no_income_ex);
                }
            }
        });

    }

    private double calculateTax(double salary) {
        double tax;
        if (contract.equals(contracts[0])) {
            tax = calculateDPPTax(salary);
        } else if (contract.equals(contracts[1])) {
            tax = calculateDPCTax(salary);
        } else if (contract.equals(contracts[2])) {
            tax = calculateHPPTax(salary);
        } else {
            tax = calculateZPPTax(salary);
        }

        return tax;
    }

    private double calculateDPPTax(double salary) {
        double tax = 0;
        if(salary<=10000){
            if(!isPKD){
                tax = 0.15*salary;
            } else {
                tax = 0;
            }
        } else {
            if(!isPKD){
                tax+=0.15*salary;
            }
        }
        return tax;
    }

    private double calculateDPCTax(double salary) {
        return 0.0;
    }

    private double calculateHPPTax(double salary) {
        return 0.0;
    }

    private double calculateZPPTax(double salary) {
        return 0.0;
    }
}

