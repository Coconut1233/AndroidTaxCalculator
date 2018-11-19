package txcalc.taxcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
    final static int soczdrav = 1100;
    final String contract = MainActivity.getContract();
    final boolean isStudent = MainActivity.isStudent();
    final boolean isZTP = MainActivity.isZTP();
    final String[] contracts = MainActivity.getContracts();
    final boolean isPKD = MainActivity.isPKD();
    private boolean partner = MainActivity.isPartner();
    private boolean partner_ZTP = MainActivity.isPartner_ZTP();
    private int invalidity = MainActivity.getInvalidity();
    private int children = MainActivity.getChildren();
    private int childrenZTP = MainActivity.getChildrenZTP();

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
                    cleanInocme.setText(String.valueOf(salary - cTax));
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
        if (salary <= 10000) {
            tax = 0.15 * salary;
            tax = creditMontly(tax);
        } else {
                int taxBase = (int) (Math.ceil((salary * 1.34) / 100) * 100);
                tax += taxBase * 0.15;
                tax += soczdrav;
                tax = creditMontly(tax);
        }
        return tax;
    }

    private double calculateDPCTax(double salary) {
        //TODO
        return 0.0;
    }

    private double calculateHPPTax(double salary) {
        //TODO
        return 0.0;
    }

    private double calculateZPPTax(double salary) {
        //TODO
        return 0.0;
    }

    private double creditMontly(double tax) {
        if(isStudent){
            tax -= Credits.STUDENT.getCredit()/12;
        }
        if(isZTP){
            tax -= Credits.ZTP.getCredit()/12;
        }
        if(isPKD){
            tax -= Credits.PKD.getCredit()/12;
        }
        if(partner){
            tax -= Credits.PARTNER.getCredit()/12;
        }
        if (partner_ZTP){
            tax -= Credits.PARTNER_ZTP.getCredit();
        }
        if(children==1){
            tax -= Credits.CHILDREN_1.getCredit();
        }
        if(children==2){
            tax -= Credits.CHILDREN_2.getCredit();
        }
        if(children==3){
            tax -= Credits.CHILDREN_3.getCredit();
        }
        if(childrenZTP==1){
            tax -= Credits.CHILDREN_1_ZTP.getCredit();
        }
        if(childrenZTP==2){
            tax -= Credits.CHILDREN_2_ZTP.getCredit();
        }
        if(children==3){
            tax -= Credits.CHILDREN_3_ZTP.getCredit();
        }
        if(invalidity==1){
            tax -= Credits.INVALIDITY_1_2.getCredit();
        }
        if(invalidity==3){
            tax -= Credits.INVALIDITY_3.getCredit();
        }
        return tax;
    }
}

