package me.superbiebel.punishmentmanager.utils;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {
    public static Class<?> stringToclazz(String pathToClass) throws ClassNotFoundException {
        return Class.forName(pathToClass);
    }
    public static boolean checkIfInherits(Class<?> clazz, Class<?> parentClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object object = clazz.getDeclaredConstructor().newInstance();
        return clazz.isAssignableFrom(parentClass);
    }
    public static Object newInstance(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return clazz.getDeclaredConstructor().newInstance();
    }
    public static Object stringToInstance(String pathToClass) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return newInstance(stringToclazz(pathToClass));
    }
    public static Object stringToInstance(String pathToClass, Class<?> parentclazz) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> clazz = stringToclazz(pathToClass);
        if (checkIfInherits(clazz,parentclazz)){
            return newInstance(clazz);
        } else throw new IllegalArgumentException("The class does not inherit from the parent class");
    }
}
