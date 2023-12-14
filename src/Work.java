import java.util.Scanner;
public class Work {
    final static int MINYEAR = 2002;
    final static int MAXYEAR = 2022;
    final static int STARTYEAR = enterYear();
    final static double HUNDRED = 100.0;
    final static double GROW = 0.5;
    public static boolean out = false;

    public static int enterYear() {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if (x == 2022) {
            return x + 1;
        }
        return x;
    }

    public static double rasCapital(double outProc) {

        int indexForCalc = STARTYEAR - MINYEAR;
        double capital = Constants.MOEX_RATE[indexForCalc];
        double cost = rc(capital, outProc);

        for (int count = Work.STARTYEAR; count < Work.MAXYEAR; ++count) {

            double inflation;
            double changeIMB;

            if (capital < cost) {
                outProc -= GROW;
                out = true;
                break;
            }

            capital = Work.capitalMinusCost(capital, cost);
            inflation = Work.inflationCurrentYear(indexForCalc);
            cost += Work.recalculcWithdrawalAmount(cost, inflation);
            changeIMB = Work.IndexChange(indexForCalc);
            capital += Work.CapitalF(capital, changeIMB);

            indexForCalc++;
        }
        return outProc;
    }

    public static double rc(double capital, double outProc) {
        return capital * outProc / 100;
    }

    public static double capitalMinusCost(double capital, double cost) {
        return capital - cost;
    }

    public static double inflationCurrentYear(int indexForCalc) {
        return Constants.INFLATION_RATE[indexForCalc];
    }

    public static double recalculcWithdrawalAmount(double withdrawalAmount, double inflation) {
        return inflation / 100 * withdrawalAmount;
    }

    public static double IndexChange(int imbYear) {
        return (Constants.MOEX_RATE[imbYear + 1] - Constants.MOEX_RATE[imbYear]) * 100 /
                Constants.MOEX_RATE[imbYear];
    }

    public static double CapitalF (double capital, double changeIMB) {
        return capital * changeIMB / 100;
    }

}
