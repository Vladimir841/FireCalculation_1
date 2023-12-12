class FireCalculation {
    public static void main(String[] args) {

        final double GROWTHPERCENTAGE = 0.5;

        finish:

        for (double outProc = 0.0; outProc <= Work.HUNDRED; outProc += GROWTHPERCENTAGE) {

            int indexForCalc;
            double cost;
            double capital;

            if (Work.STARTYEAR == Work.MAXYEAR) {
                System.out.println(Work.HUNDRED);
                break;
            }

            indexForCalc = Work.STARTYEAR - Work.MINYEAR;
            capital = Work.capitalInit(indexForCalc);
            cost = Work.Rc(capital, outProc);

            for (int count = Work.STARTYEAR; count < Work.MAXYEAR; ++count) {

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
                    break finish;
                }
            }
        }
    }
}