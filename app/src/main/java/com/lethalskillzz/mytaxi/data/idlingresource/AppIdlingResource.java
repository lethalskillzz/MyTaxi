package com.lethalskillzz.mytaxi.data.idlingresource;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class AppIdlingResource implements IdlingResource {

  @Nullable
  private volatile ResourceCallback callback;

  private AtomicBoolean isIdleNow = new AtomicBoolean(true);

  @Override
  public String getName() {
    return this.getClass().getName();
  }

  @Override
  public boolean isIdleNow() {
    return isIdleNow.get();
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    this.callback = callback;
  }

  public void setIdleState(boolean idleState) {
    isIdleNow.set(idleState);
    if (idleState && callback != null) {
      callback.onTransitionToIdle();
    }
  }
}
