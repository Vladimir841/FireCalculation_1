import java.util.*;

class FireCalculation {
    public static void main(String[] args) throws OutOfRangeException {

        final int MINYEAR = 2002;            // Минимальное допустимое значение для ввода
        final int MAXYEAR = 2021;            // Максимальное допустимое значение для ввода

        Scanner sc = new Scanner(System.in);
        final int STARTYEAR = sc.nextInt();  // Считываем данные от пользователя

        try {
            // Если год в диапозоне 2002 - 2021 включительно тогда
            if (STARTYEAR >= MINYEAR && STARTYEAR <= MAXYEAR) {

                int indexForCalc;

                // 100.0%
                final double HUNDRED = 100.0;

                // Шаг прироста процента изъятия
                final double GROWTHPERCENTAGE = 0.5;

                double cost;
                double capital;

                for (double outProc = 0.0; outProc <= HUNDRED; outProc += GROWTHPERCENTAGE) {

                    //Если год начала жизни на проценты 2021 снимаем все и доживаем до 2022 года
                    if (STARTYEAR == MAXYEAR) {
                        System.out.println(HUNDRED);
                        break;
                    }

                    indexForCalc = STARTYEAR - MINYEAR;     // Индекс для обращения к данным в классе Constants
                    capital = capitalInit(indexForCalc); // Инициализация переменной capital
                    cost = capital * outProc / HUNDRED;  // Сколько изымается ( числом )

                    double inflation;
                    double changeIMB;

                    for (int count = STARTYEAR; count < MAXYEAR; ++count) {

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
                            outProc -= GROWTHPERCENTAGE; // Процент изъятия уменьшается на 0.5 ( шаг )
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




