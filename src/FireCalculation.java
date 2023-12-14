public class FireCalculation {
    public static void main(String[] args) {

        double answer = 0.0;

        for(double outProc = Work.GROW; outProc <= Work.HUNDRED; outProc += Work.GROW) {

            answer = Work.rasCapital(outProc);

            if (Work.out) {
                break;
            }
        }
        System.out.println(answer);
    }
}