package trim;

import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class AnnotationHandler {
    public String basePacket;
    public String[] annotations;

    public String getBasePacket() {
        return basePacket;
    }

    public void setBasePacket(String basePacket) {
        this.basePacket = basePacket;
    }

    public String[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String[] annotations) {
        this.annotations = annotations;
    }
    public List<Class<?>>  scan() throws IOException, ClassNotFoundException {
        String p = AnnotationHandler.class.getClassLoader().getResource("").getPath() + basePacket.replace(".","/");
        File path = new File(p);
        File[] clazzFileList = path.listFiles();
        List<Class<?>> classList = new LinkedList<Class<?>>();
        for (File c : clazzFileList) {
            System.out.println(basePacket+c.getName());
            Class<?> clazz = Class.forName(basePacket + c.getName());
            classList.add(clazz);
//            try {
//                Class<?> clazz = Class.forName(basePacket + c.getName());
//
//                Method[] methods = clazz.getDeclaredMethods();
//                for (Method m : methods) {
//                    Annotation a = m.getAnnotation(Override.class);
//                    if (a == null) {
//                        System.out.println(m.getName()  + "没有该注解");
//                    } else {
//
//                    }
//                }
//            } catch (ClassNotFoundException e) {
//                continue;
//            }
        }
        return classList;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AnnotationHandler annotationHandler = new AnnotationHandler();
        annotationHandler.setBasePacket("trim.client");
        annotationHandler.scan();
    }
}
