package com.test.spring.mvc;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterNameUtil {

    public static void main(String[] args) {
        List<String> paramterNamesJava8 = getParameterNameJava8(
                ParameterNameUtil.class, "getParameterNameJava8");
        paramterNamesJava8.forEach((x) -> System.out.print(x + ",\t"));

        System.out.println("\n----------------");

        List<String> paramterNamesBySpring = getParamterNameBySpring(
                ParameterNameUtil.class, "getParameterNameJava8");
        paramterNamesBySpring.forEach((x) -> System.out.print(x + ",\t"));
    }

    public static List<String> getParameterNameJava8(Class clazz, String methodName) {
        List<String> paramterList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                //直接通过method就能拿到所有的参数
                Parameter[] params = method.getParameters();
                for (Parameter parameter : params) {
                    paramterList.add(parameter.getName());
                }
            }
        }
        return paramterList;
    }

    public static List<String> getParamterNameBySpring(Class clazz, String methodName) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                //获取到该方法的参数们
                String[] params = u.getParameterNames(method);
                return Arrays.asList(params);
            }
        }
        return null;
    }
}