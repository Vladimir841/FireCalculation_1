import java.util.Scanner;

public class FireCalculation {

    final static int minYear = 2002;
    final static int maxYear = 2021;
    final static double hundred = 100.0;
    final static double grow = 0.5;
    public static boolean out = false;
    final static int startYear = enterYear();
    int indexForCalc;
    double capital;
    double cost;
    double inflation;
    double changeIMB;
    public static void main(String[] args) {

        double answer = 0.0;
        FireCalculation exampleForCalc = new FireCalculation();

        for(double outProc = grow; outProc <= hundred; outProc += grow) {
            answer = exampleForCalc.rasCapital(outProc);

            if (out) {
                break;
            }
        }
        System.out.println(answer);
    }


    public  double rasCapital(double outProc) {
        initS(outProc);
        for (int count = startYear; count < maxYear; ++count) {
            calculation();

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
    public void initS(double outProc) {
        indexForCalc = startYear - minYear;
        capital = Constants.MOEX_RATE[indexForCalc + 1];
        cost = capital * outProc / hundred;
    }

    public void calculation() {
        capital = capital - cost;
        inflation = Constants.INFLATION_RATE[indexForCalc];
        cost += inflation / hundred * cost;
        changeIMB = IndexChange(indexForCalc, hundred);
        capital += capital * changeIMB / hundred;

        indexForCalc++;
    }
}