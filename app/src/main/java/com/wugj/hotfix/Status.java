package com.wugj.hotfix;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/16
 * </br>
 * version:
 */



@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@IntDef({1,2})
public @interface Status {

}
