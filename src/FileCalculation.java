import java.util.*;

class FireCalculation {
    public static void main (String[] args) {

        double capital, outProc, outProcDigit;

        int startYear, endYear, indexForCalc;

        Scanner sc = new Scanner(System.in);

        startYear = sc.nextInt();

        try {
            if (startYear >= 2002 && startYear <= 2021) {

                endYear = 2022;
                indexForCalc = startYear - 2002;
                capital = Constants.MOEX_RATE[indexForCalc];
                outProc = 0.5;
                outProcDigit = capital * outProc / 100;

                for (int i = startYear; i < endYear; ++i) {

                    if (startYear == 2021) {
                        System.out.println(100);
                        break;
                    }

                    capital -= outProcDigit;

                    double def = (Constants.MOEX_RATE[indexForCalc + 1] - Constants.MOEX_RATE[indexForCalc]) * 100 /
                            Constants.MOEX_RATE[indexForCalc];
                    capital += def / 100 * capital;

                    double inf = Constants.INFLATION_RATE[indexForCalc];
                    outProcDigit += inf / 100 * outProcDigit;

                    indexForCalc++;

                    if (capital > 0 && i == endYear - 1) {
                        indexForCalc = startYear - 2002;
                        capital = Constants.MOEX_RATE[indexForCalc];
                        outProc += 0.5;
                        outProcDigit = capital * outProc / 100;
                        capital -= outProcDigit;
                        i = startYear;
                    }

                    if (capital < outProcDigit) {
                        outProc -= 0.5;
                        System.out.println(outProc);
                        break;
                    }
                }
            } else {
                throw new OutOfRangeException("throws Exception...");
            }
        } catch (OutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }
}