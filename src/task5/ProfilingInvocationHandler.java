package task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ProfilingInvocationHandler implements InvocationHandler {
        private Function target;

        public ProfilingInvocationHandler(Function target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.nanoTime();
            Object result = method.invoke(target, args);
            long endTime = System.nanoTime();
            System.out.println("[" + target.getClass().getSimpleName() + "]." + method.getName() +
                    (args != null ? "(" + Arrays.toString(args) + ")" : "") +
                    " took " + (endTime - startTime) + " ns");
            return result;
        }
}
