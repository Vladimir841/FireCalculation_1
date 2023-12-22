public class FireCalculation {
    public static void main(String[] args) {

        double answer = 0.0;
        Work exampleForCalc = new Work();

        for(double outProc = Work.grow; outProc <= Work.hundred; outProc += Work.grow) {

            answer = exampleForCalc.rasCapital(outProc);

            if (Work.out) {
                break;
            }
        }
        System.out.println(answer);
    }
}