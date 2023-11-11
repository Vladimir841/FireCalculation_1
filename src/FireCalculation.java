import java.util.*;

class FireCalculation {
    public static void main(String[] args) throws OutOfRangeException {

        // Минимальное допустимое значение для ввода
        final int MINYEAR = 2002;

        // Максимальное допустимое значение для ввода
        final int MAXYEAR = 2021;

        Scanner sc = new Scanner(System.in);

        // Считываем данные от пользователя
        final int STARTYEAR = sc.nextInt();

        try {
            // Если год в диапозоне 2002 - 2021 включительно тогда
            if (STARTYEAR >= MINYEAR && STARTYEAR <= MAXYEAR) {

                // 100.0%
                final double HUNDRED = 100.0;

                // Шаг прироста процента изъятия
                final double GROWTHPERCENTAGE = 0.5;

                for (double outProc = 0.0; outProc <= HUNDRED; outProc += GROWTHPERCENTAGE) {

                    int indexForCalc;
                    double cost;
                    double capital;

                    //Если год начала жизни на проценты 2021 снимаем все и доживаем до 2022 года
                    if (STARTYEAR == MAXYEAR) {
                        System.out.println(HUNDRED);
                        break;
                    }

                    // Индекс для обращения к данным в классе Constants
                    indexForCalc = STARTYEAR - MINYEAR;

                    // Инициализация переменной capital
                    capital = capitalInit(indexForCalc);

                    // Сколько изымается ( числом )
                    cost = capital * outProc / HUNDRED;

                    for (int count = STARTYEAR; count < MAXYEAR; ++count) {

                        double inflation;
                        double changeIMB;

                        // В начале года вычитаем расходы
                        capital = capitalMinusCost(capital, cost);

                        // Получаем данные об инфляции
                        inflation = inflationCurrentYear(indexForCalc);

                        // Увеличение расходов в результате инфляции
                        cost += recalculcWithdrawalAmount(cost, inflation, HUNDRED);

                        // Как и на сколько в % изменился ИМБ за год
                        changeIMB = IndexChange(indexForCalc);

                        // Изменение капитала в зависимости от изменения ИМБ
                        capital += capital * changeIMB / HUNDRED;

                        // Увеличиваен иднекс для обращения в класс Coststants
                        indexForCalc++;

                        // Если тут ИСТИНА, значит при таком проценте изъятия не доживем до 2022 года
                        if (capital < cost) {

                            // Процент изъятия уменьшается на 0.5 ( шаг )
                            outProc -= GROWTHPERCENTAGE;
                            System.out.println(outProc);
                            break;
                        }
                    }
                    if (capital < cost) {
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

    private static double capitalInit(int indexForCalc) {
        return Constants.MOEX_RATE[indexForCalc];
    }

    private static double capitalMinusCost(double capital, double withdrawalAmount) {
        return capital - withdrawalAmount;
    }

    private static double IndexChange(int imbYear) {
        final double HUNDRED_1 = 100;
        return (Constants.MOEX_RATE[imbYear + 1] - Constants.MOEX_RATE[imbYear]) * HUNDRED_1 /
                Constants.MOEX_RATE[imbYear];
    }

    private static double recalculcWithdrawalAmount(double withdrawalAmount, double inflation, double hundred) {
        return inflation / hundred * withdrawalAmount;
    }

    private static double inflationCurrentYear(int indexForCalc) {
        return Constants.INFLATION_RATE[indexForCalc];
    }

}




