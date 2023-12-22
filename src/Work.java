import java.util.Scanner;
public class Work {
    final static int minYear = 2002;
    final static int maxYear = 2021;
    final static double hundred = 100.0;
    final static double grow = 0.5;
    public static boolean out = false;
    final static int startYear = enterYear();

    public static double rasCapital(double outProc) {
        int indexForCalc;
        double capital;
        double cost;

        indexForCalc = startYear - minYear;
        capital = Constants.MOEX_RATE[indexForCalc + 1];
        cost = capital * outProc / hundred;

        for (int count = startYear; count < maxYear; ++count) {
            double inflation;
            double changeIMB;

            capital = capital - cost;
            inflation = Constants.INFLATION_RATE[indexForCalc];
            cost += inflation / hundred * cost;
            changeIMB = IndexChange(indexForCalc, hundred);
            capital += capital * changeIMB / hundred;

            indexForCalc++;

            if (capital < cost) {
                outProc -= grow;
                out = true;
                break;
            }
        }
        return outProc;
    }

    public static double IndexChange(int imbYear, double hundred) {
        return (Constants.MOEX_RATE[imbYear + 1] - Constants.MOEX_RATE[imbYear]) * hundred /
                Constants.MOEX_RATE[imbYear];
    }
    public static int enterYear() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}