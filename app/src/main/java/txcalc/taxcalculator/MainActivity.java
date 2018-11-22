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
    private static boolean partner;
    private static boolean partner_ZTP;
    private static int invalidity;
    private static int children;
    private static int childrenZTP;

    final Spinner hasChildren = findViewById(R.id.children);
    final Spinner hasChildrenZTP = findViewById(R.id.childrenZTP);
    final Spinner hasInvalidity = findViewById(R.id.invalidity);
    final Spinner droplist = findViewById(R.id.contracts);
    final CheckBox hasPartner = findViewById(R.id.partner);
    final CheckBox hasPartnerZTP = findViewById(R.id.partnerZTP);
    final CheckBox isStudent = findViewById(R.id.isStudent);
    final CheckBox isZTP = findViewById(R.id.isZTP);
    final CheckBox isPKD = findViewById(R.id.isPKD);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasChildren.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"0","1 "+R.string.child, "2 "+R.string.child_pl, "3 "+R.string.child_pl}));
        hasChildrenZTP.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"0","1 "+R.string.child, "2 "+R.string.child_pl, "3 "+R.string.child_pl}));
        hasInvalidity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"0",R.string.invalidity+" 1/2",R.string.invalidity+" 3"}));
        droplist.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, contracts));
        Button toCalc = findViewById(R.id.toCalc);
        //TODO partner listener
        hasPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasPartnerZTP.isChecked()){
                    hasPartnerZTP.toggle();
                }
            }
        });
        hasPartnerZTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasPartner.isChecked()){
                    hasPartnerZTP.toggle();
                }
            }
        });
        toCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contract = droplist.getSelectedItem().toString();

                if(hasChildren.getSelectedItem().toString().contains("0")){
                    children = 0;
                } else if(hasChildren.getSelectedItem().toString().contains("1")){
                    children = 1;
                } else if(hasChildren.getSelectedItem().toString().contains("2")){
                    children = 2;
                } else {
                    children = 3;
                }

                if(hasChildrenZTP.getSelectedItem().toString().contains("0")){
                    childrenZTP = 0;
                } else if(hasChildrenZTP.getSelectedItem().toString().contains("1")){
                    childrenZTP = 1;
                } else if(hasChildrenZTP.getSelectedItem().toString().contains("2")){
                    childrenZTP = 2;
                } else {
                    childrenZTP = 3;
                }

                if(hasInvalidity.getSelectedItem().toString().contains("0")){
                    invalidity = 0;
                } else if(hasInvalidity.getSelectedItem().toString().contains("1")){
                    invalidity = 1;
                } else if(hasInvalidity.getSelectedItem().toString().contains("3")){
                    invalidity = 3;
                }

                if(hasPartner.isChecked()){
                    partner = true;
                }

                if(hasPartnerZTP.isChecked()){
                    partner_ZTP = true;
                }

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

    public static int getChildrenZTP() {
        return childrenZTP;
    }

    public static int getChildren() {

        return children;
    }

    public static int getInvalidity() {

        return invalidity;
    }

    public static boolean isPartner_ZTP() {

        return partner_ZTP;
    }

    public static boolean isPartner() {

        return partner;
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

