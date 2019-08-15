package com.wugj.hotfix.fix;

import java.lang.reflect.Array;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/7/25
 * </br>
 * version:
 */
public class ArrayUtil {

    public static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k) {
            if (k < i) {
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }
}
