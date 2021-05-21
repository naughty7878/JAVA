package com.test.spring.mymvc.context;

import com.test.spring.mymvc.annotation.Autowired;
import com.test.spring.mymvc.annotation.Controller;
import com.test.spring.mymvc.annotation.Service;
import com.test.spring.mymvc.xml.XmlPaser;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SpringMVC容器
 */
public class WebApplicationContext {

    // 配置路径
    String contextConfigLocation;

    // 类名集合  用于存放 bean 的权限名|包名.类名
    List<String> classNameList = new ArrayList<>();

    // IOC容器
    public Map<String, Object> iocMap = new ConcurrentHashMap<>();

    // 无参构造器
    public WebApplicationContext() {
    }

    // 有参构造器
    public WebApplicationContext(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
    }

    // 刷新容器
    public void refresh() {

        // 1、进行解析SpringMVC配置文件操作
        String pack = XmlPaser.parserXml(contextConfigLocation.split(":")[1]);
        String[] packs = pack.split(",");

        // 2、进行包扫描
        for (String pa : packs) {
            excuteScanPackage(pa);
        }

        // 3、实例化容器中的Bean
        executeInstance();

        // 4、进行自动注入操作
        executeAutowired();
    }

    /**
     * 进行自动注入操作
     */
    public void executeAutowired() {
        // 从容器中取出bean，判断其属性上是否有@Autowired注解
        // 有的话，就进行自动注入
        iocMap.forEach((k, v) -> {
            // 获取容器中的bean
            Object bean = v;
            // 获取bean中的属性
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.isAnnotationPresent(Autowired.class)) {
                    // 获取@Autowired注解的值
                    Autowired autowiredAn = field.getAnnotation(Autowired.class);
                    String beanName = autowiredAn.value();

                    // 设置可访问
                    field.setAccessible(true);
                    try {
                        field.set(bean, iocMap.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 实例化容器中的Bean
     */
    public void executeInstance() {

        try {
            for (String className : classNameList) {
                Class<?> clazz = Class.forName(className);

                // 判断是否有@Controller注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 控制层
                    String simpleName = clazz.getSimpleName();
                    String beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1, simpleName.length());
                    iocMap.put(beanName, clazz.newInstance());
                }
                // 判断是否有@Service注解
                else if (clazz.isAnnotationPresent(Service.class)) {
                    // service层
                    Service serviceAn = clazz.getAnnotation(Service.class);
                    String beanName = serviceAn.value();
                    iocMap.put(beanName, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 扫描包
     */
    public void excuteScanPackage(String pack) {
        // com.test.spring ==> com/test/spring
        URL url = this.getClass().getClassLoader().getResource("/" + pack.replaceAll("\\.", "/"));
        String path = url.getFile();
        // 得到扫描的包
        File dir = new File(path);
        // 遍历
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                // 当前是个目录
                excuteScanPackage(pack + "." + f.getName());
            } else {
                // 当前是个文件
                // 获取全路径 com.test.spring.mvc.controller.User
                String className = pack + "." + f.getName().replace(".class", "");
                classNameList.add(className);
                System.out.println(className);
            }
        }
    }


}
