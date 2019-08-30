package com.wugj.hotfix.dex;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/15
 * </br>
 * version:
 */
@Deprecated
public class FindClass {



    private static Field field(String className , String fieldName){
        Field field = null;
        try {
            Class<?> clazz = Class.forName(className);
            field =  clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 返回安装包下所有DexFile集合
     * @param context
     * @return
     */
    private static Collection<DexFile> getDexFiles(Context context){

        //DexFile集合
        Set<DexFile> files = new LinkedHashSet<>();

        try {
            BaseDexClassLoader classLoader = (BaseDexClassLoader) context.getClassLoader();
            Field pathListField = field("dalvik.system.BaseDexClassLoader", "pathList");
            Object pathList = pathListField.get(classLoader); // Type is DexPathList

            Field dexElementsField = field("dalvik.system.DexPathList", "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);


            Field dexFileField = field("dalvik.system.DexPathList$Element", "dexFile");


            for (Object it : dexElements){
                DexFile dexFile = (DexFile) dexFileField.get(it);
                files.add(dexFile);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return files;
    }

    /**
     * 获取DexFile中所有的class名称
     * @param df
     * @param context
     * @return
     */
    public static List<String> getClassName(DexFile df,Context context) {
        List<String> classNameList = new ArrayList<>();
        //获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
        Enumeration<String> enumeration = df.entries();
        while (enumeration.hasMoreElements()) {//遍历
            String className = enumeration.nextElement();

            //在当前所有可执行的类里面查找包含有该包名的所有类
            if (className.contains(context.getPackageName())) {
                classNameList.add(className);
            }
        }
        return classNameList;
    }


    /**
     * 便利所有'Class<?>' 获取ChangeClass标注的类，
     * @param context
     */
    public static void getChangeClass(Context context){

        //获取所有dex
        Set<DexFile> dexFiles = (Set) getDexFiles(context);

        for (DexFile file : dexFiles){
            //获取所有class名
            List<String> classNameList = getClassName(file,context);

            try {
                for (String str : classNameList) {

                    //当前类是否是ChangeClass注解标注
                    Class<?> clazz = Class.forName(str);
                    ChangeClass changeClass = clazz.getAnnotation(ChangeClass.class);
                    if (changeClass != null) {
                        Log.e("打印修改的class", str);
                    }

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
