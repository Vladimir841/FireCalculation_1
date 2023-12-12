import java.util.Scanner;

public class Work {

    public static double capitalMinusCost(double capital, double withdrawalAmount) {
        return capital - withdrawalAmount;
    }

    public static double CapitalF (double capital, double changeIMB) {

        return capital * changeIMB / 100;
    }

    public static double Rc(double capital, double outProc) {
        return capital * outProc / 100;
    }

    public static double capitalInit(int indexForCalc) {

        return Constants.MOEX_RATE[indexForCalc];
    }

    public static double IndexChange(int imbYear) {
        return (Constants.MOEX_RATE[imbYear + 1] - Constants.MOEX_RATE[imbYear]) * 100 /
                Constants.MOEX_RATE[imbYear];
    }

    public static double recalculcWithdrawalAmount(double withdrawalAmount, double inflation) {
        return inflation / 100 * withdrawalAmount;
    }

    public static double inflationCurrentYear(int indexForCalc) {
        return Constants.INFLATION_RATE[indexForCalc];
    }

    public static void Pr(double outProc, double GROWTHPERCENTAGE) {
        outProc -= GROWTHPERCENTAGE;
        System.out.println(outProc);
    }

    public static int EnterYear() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
