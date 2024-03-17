package task5;

import java.lang.reflect.Proxy;


public class Main {
    public static void main(String[] args) {
        Function f1 = new BigFunc(3.14);
        Function f2 = new SquareFunc();

        Function f1ProfilingProxy = (Function) Proxy.newProxyInstance(
                Function.class.getClassLoader(),
                new Class[]{Function.class},
                new ProfilingInvocationHandler(f1)
        );

        Function f2TracingProxy = (Function) Proxy.newProxyInstance(
                Function.class.getClassLoader(),
                new Class[]{Function.class},
                new TracingInvocationHandler(f2)
        );

        System.out.println("F1: " + f1.eval(0.0));
        System.out.println("F2: " + f2.eval(1.0));

        System.out.println();

        System.out.println("F1: " + f1ProfilingProxy.eval(0.0));
        System.out.println("F2: " + f2TracingProxy.eval(1.0));
    }
}
