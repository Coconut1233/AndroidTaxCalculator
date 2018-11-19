package txcalc.taxcalculator;

public enum Credits {
    PKD(24840),
    CHILDREN_1(15204),
    CHILDREN_2(19404),
    CHILDREN_3(24204),
    CHILDREN_1_ZTP(15204*2),
    CHILDREN_2_ZTP(19404*2),
    CHILDREN_3_ZTP(24204*2),
    PARTNER(24840),
    PARTNER_ZTP(24840*2),
    INVALIDITY_1_2(2520),
    INVALIDITY_3(5040),
    ZTP(16140),
    STUDENT(4020);

    private final int credit;

    Credits(int credit){
        this.credit=credit;
    }

    public int getCredit() {
        return credit;
    }
}
