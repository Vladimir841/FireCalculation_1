class FireCalculation {
    public static void main(String[] args) {

        final int MINYEAR = 2002;
        final int MAXYEAR = 2021;

        final int STARTYEAR = Work.EnterYear();

        final double HUNDRED = 100.0;
        final double GROWTHPERCENTAGE = 0.5;

        for (double outProc = 0.0; outProc <= HUNDRED; outProc += GROWTHPERCENTAGE) {

            int indexForCalc;
            double cost;
            double capital;

            if (STARTYEAR == MAXYEAR) {
                System.out.println(HUNDRED);
                break;
            }

            indexForCalc = STARTYEAR - MINYEAR;
            capital = Work.capitalInit(indexForCalc);
            cost = Work.Rc(capital, outProc);

            for (int count = STARTYEAR; count < MAXYEAR; ++count) {

                double inflation;
                double changeIMB;

                capital = Work.capitalMinusCost(capital, cost);
                inflation = Work.inflationCurrentYear(indexForCalc);
                cost += Work.recalculcWithdrawalAmount(cost, inflation);
                changeIMB = Work.IndexChange(indexForCalc);
                capital += Work.CapitalF(capital, changeIMB);

                indexForCalc++;

                if (capital < cost) {
                    Work.Pr(outProc, GROWTHPERCENTAGE);
                    break;
                }
            }
            if (capital < cost) {
                break;
            }
        }
    }
}




