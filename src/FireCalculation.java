import java.util.*;

class FireCalculation {
    public static void main(String[] args) {

        double capital, outProc, cost, growthPercentage, changeIMB, inflation;
        int startYear, indexForCalc, hundred, minYear, maxYear;

        hundred = 100;          // 100%
        outProc = 0.0;          // Начальное значение % изъятия
        growthPercentage = 0.5; // Шаг увеличения процента изъятия

        minYear = 2002;         // Минимальное допустимое значение для ввода
        maxYear = 2021;         // Максимальное допустимое значение для ввода

        Scanner sc = new Scanner(System.in);
        startYear = sc.nextInt();

        try {
            // Если год в диапозона 2002 - 2021 включительно тогда
            if (startYear >= minYear && startYear <= maxYear) {

                indexForCalc = startYear - minYear;  // Индекс для обращения к данным в классе Constants
                capital = capitalInit(indexForCalc); // Инициализация переменной capital
                cost = capital * outProc / hundred;  // Сколько выводим на расходы (цифрой)

                for (int count = startYear; count <= maxYear; ++count) {

                    //Если год начала жизни на проценты 2021 снимаем все и доживаем до 2022 года
                    if (startYear == maxYear) {
                        System.out.println(hundred);
                        break;
                    }
                    // В начале года вычитаем расходы
                    capital = capitalMinusCost(capital, cost);

                    // Инфляция на текущий год
                    inflation = inflationCurrentYear(indexForCalc);

                    // Увеличение расходов в результате инфляции
                    cost += recalculcWithdrawalAmount(cost, inflation, hundred);

                    // Как и на сколько в % изменился ИМБ за год
                    changeIMB = IndexChange(indexForCalc);

                    // Изменение капитала в зависимости от изменения ИМБ
                    capital += capital * changeIMB / hundred;

                    indexForCalc++; // Увеличиваен иднекс для обращения в класс Coststant

                    /*

                       Если остается слишком много денег в наследство, сбрасываем переменные на
                       начальные значения, увеличиваем процент изъятия на 0.5% и пересчитываем.

                    */

                    if (capital > 0 && count == maxYear) {
                        indexForCalc = startYear - minYear;
                        capital = Constants.MOEX_RATE[indexForCalc];
                        outProc += growthPercentage;        //Сколько изымается ( в процентах )
                        cost = capital * outProc / hundred; //Сколько изымается ( числом )
                        capital -= cost;
                        count = startYear;
                    }

                    // Если тут ИСТИНА, значит при таком проценте изъятия не доживем до 2022 года
                    if (capital < cost) {
                        outProc -= growthPercentage; // Процент изъятия уменьшается на 0.5 ( шаг )
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

    private static double capitalInit(int indexForCalc) {
        return Constants.MOEX_RATE[indexForCalc];
    }

    private static double capitalMinusCost(double capital, double withdrawalAmount) {
        return capital - withdrawalAmount;
    }

    private static double IndexChange(int imbYear) {
        return (Constants.MOEX_RATE[imbYear + 1] - Constants.MOEX_RATE[imbYear]) * 100 /
                Constants.MOEX_RATE[imbYear];
    }

    private static double recalculcWithdrawalAmount(double withdrawalAmount, double inflation, int hundred) {
        return inflation / hundred * withdrawalAmount;
    }

    private static double inflationCurrentYear(int indexForCalc) {
        return Constants.INFLATION_RATE[indexForCalc + 1];
    }

}




