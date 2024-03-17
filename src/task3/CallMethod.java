package task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CallMethod  {
    public static Object invokeMethod(Object obj, String methodName, Object... args) throws FunctionNotFoundException {
    Class<?> cls = obj.getClass();

    Method method;
    try {
        method = cls.getMethod(methodName, getParameterTypes(args));
    } catch (NoSuchMethodException e) {
        throw new FunctionNotFoundException("Метод " + methodName + " не знайдено");
    }

    if(!Modifier.isPublic(method.getModifiers())){
        throw new FunctionNotFoundException("Метод " + methodName + " не є публічним");
    }

    try {
        return method.invoke(obj, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
        throw new FunctionNotFoundException("Помилка при виклику методу " + methodName + "!" , e);
    }
    }

    private static Class<?>[] getParameterTypes(Object... args){
        Class<?>[] types = new Class<?>[args.length];
        for(int i = 0; i < args.length; i++){
            types[i] = args[i].getClass();
        }
        return types;
    }

    public static void main(String[] args) {

        Object obj = new String("Hello world!");
        TestClass tc = new TestClass(1.0);

        try {
            int length = (int) invokeMethod(obj, "length");
            System.out.println("Довжина " + length);

            String upperCase = (String) invokeMethod(obj, "toUpperCase");
            System.out.println("Рядок у верхньому регістрі " + upperCase);

            double exp = (double) invokeMethod(tc, "exp", new Object[]{});
            System.out.println("Відповідь " + exp);
        } catch (FunctionNotFoundException e){
            System.out.println("Помилка " + e.getMessage());
        }

    }
}
