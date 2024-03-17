package task5;

public class BigFunc implements Function {
    private double a;

    public BigFunc(double a) {
        this.a = a;
    }

    @Override
    public double eval(double x) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }
}
