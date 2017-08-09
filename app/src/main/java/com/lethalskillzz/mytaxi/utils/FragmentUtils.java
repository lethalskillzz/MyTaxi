package com.lethalskillzz.mytaxi.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class FragmentUtils {

  public static void addFragmentTo(@NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment, int frameId) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment);
    transaction.commit();
  }

  public static void replaceFragmentIn(@NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment, int frameId) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(frameId, fragment);
    transaction.commit();
  }
}
