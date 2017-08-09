package com.lethalskillzz.mytaxi.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {

}
