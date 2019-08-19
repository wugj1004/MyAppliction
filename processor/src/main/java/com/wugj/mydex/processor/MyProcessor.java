package com.wugj.mydex.processor;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import mydex.wugj.com.annonation.MyAnnotation;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/19
 * </br>
 * version:
 */



@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("mydex.wugj.com.annonation.MyAnnotation")
@AutoService(Processor.class) //使用此注解自动注册，
public class MyProcessor extends AbstractProcessor {



    /**
     * init()方法会被注解处理工具调用，并输入ProcessingEnviroment参数。
     * ProcessingEnviroment提供很多有用的工具类Elements, Types 和 Filer
     * @param processingEnv 提供给 processor 用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让你查询出包含特定注解的被注解元素
     * @param annotations   请求处理的注解类型
     * @param roundEnv  有关当前和以前的信息环境
     * @return  如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     *          如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //记录修改类
        StringBuffer stringBuffer = new StringBuffer();

        // roundEnv.getElementsAnnotatedWith()返回使用给定注解类型的元素
        for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotation.class)) {
            System.out.println("------------------------------");
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;


                String name = typeElement.getQualifiedName().toString();
                // 输出元素名称
                System.out.println(name);

                String[] strs = name.split("\\.");
                StringBuffer sb = new StringBuffer();
                for (String s: strs){
                    sb.append(s).append("/");
                }

                if (sb.length()>1){
                    String newStr = sb.substring(0,sb.length()-1);
                    StringBuffer ss = new StringBuffer(newStr);
                    ss.append(".class");

                    stringBuffer.append(ss).append(" ");
                }








                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(MyAnnotation.class).value());
            }
            System.out.println("------------------------------");
        }

        // 输出修改记录
        System.out.println(stringBuffer);
        return false;
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(MyAnnotation.class.getCanonicalName());
        return annotataions;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     * @return  使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
