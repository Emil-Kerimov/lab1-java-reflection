package task2;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Створення об'єкту...");
        Check obj = new Check(3.0, 4.0);
        System.out.println("Стан об'єкту:");
        printObjectState(obj);

        System.out.println("Виклик методу...");
        printOpenMethods(obj);

        System.out.println("Введіть порядковий номер методу [1, " + openMethodsCount(obj) + "]:");
        int choice = scanner.nextInt();
        callMethod(obj, choice);
    }

    private static void printObjectState(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getType().getName() + " " + field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printOpenMethods(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        int count = 1;
        System.out.println("Список відкритих методів:");
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                System.out.println(count + "). " + method.toString());
                count++;
            }
        }
    }

    private static int openMethodsCount(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        int count = 0;
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                count++;
            }
        }
        return count;
    }

    private static void callMethod(Object obj, int choice) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        int count = 0;
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                count++;
                if (count == choice) {
                    try {
                        Object result = method.invoke(obj);
                        System.out.println("Результат виклику методу: " + result.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
