package task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TracingInvocationHandler implements InvocationHandler {
        private Function target;

        public TracingInvocationHandler(Function target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.print("[" + target.getClass().getSimpleName() + "]." + method.getName() +
                    (args != null ? "(" + Arrays.toString(args) + ")" : ""));
            if (args != null && args.length > 0)
                System.out.print(" = " + target.eval((double) args[0]));
            System.out.println();
            return method.invoke(target, args);
        }
}
