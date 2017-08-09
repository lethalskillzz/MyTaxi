package com.lethalskillzz.mytaxi.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

