package task1;

import java.lang.reflect.*;

public class Reflection {
    public static String getFullDescription(String className) throws ClassNotFoundException {
        Class<?> cls = Class.forName(className);
        return getFullDescription(cls);
    }

    public static String getFullDescription(Class<?> cls) {
        StringBuilder sb = new StringBuilder();

        sb.append("Package: ").append(cls.getPackageName()).append("\n");

        sb.append(Modifier.toString(cls.getModifiers())).append(" ");
        if (cls.isInterface()) {
            sb.append("interface ");
        } else if (cls.isArray()) {
            sb.append("class ");
            sb.append(cls.getComponentType().getName()).append("[]");
        } else {
            sb.append("class ");
        }
        sb.append(cls.getSimpleName()).append("\n");

        if (cls.getSuperclass() != null) {
            sb.append("extends ").append(cls.getSuperclass().getName()).append("\n");
        }

        if (cls.getInterfaces().length > 0) {
            sb.append("implements ");
            for (Class<?> interf : cls.getInterfaces()) {
                sb.append(interf.getName()).append(", ");
            }
            sb.deleteCharAt(sb.length() - 2).append("\n");
        }

        sb.append("\nFields:\n");
        for (Field field : cls.getDeclaredFields()) {
            sb.append("  ").append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getName()).append(" ");
            sb.append(field.getName()).append(";\n");
        }

        sb.append("\nConstructors:\n");
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            sb.append("  ").append(Modifier.toString(constructor.getModifiers())).append(" ");
            sb.append(cls.getSimpleName()).append("(");
            for (Class<?> parameter : constructor.getParameterTypes()) {
                sb.append(parameter.getName()).append(", ");
            }
            if (constructor.getParameterTypes().length > 0) {
                sb.deleteCharAt(sb.length() - 2);
            }
            sb.append(");\n");
        }

        sb.append("\nMethods:\n");
        for (Method method : cls.getDeclaredMethods()) {
            sb.append("  ").append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType().getName()).append(" ");
            sb.append(method.getName()).append("(");
            for (Class<?> parameter : method.getParameterTypes()) {
                sb.append(parameter.getName()).append(", ");
            }
            if (method.getParameterTypes().length > 0) {
                sb.deleteCharAt(sb.length() - 2);
            }
            sb.append(")");
            if (method.getExceptionTypes().length > 0) {
                sb.append(" throws ");
                for (Class<?> exception : method.getExceptionTypes()) {
                    sb.append(exception.getName()).append(", ");
                }
                sb.deleteCharAt(sb.length() - 2);
            }
            sb.append(";\n");
        }

        return sb.toString();
    }
}
