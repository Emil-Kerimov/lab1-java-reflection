package task3;


public class TestClass {
    private double a = 1.0;
    private double x;
    public TestClass(double x){
        this.x = x;
    }
    public double exp(){
        return (-Math.abs(a)*x)*Math.sin(x);
    }
}
