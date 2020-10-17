package com.kata.arguments.utils;

public class TestUtils {

  public static final String INSTANTIATION_ERROR = "TestUtils cannot be instantiated";

  private TestUtils() {
    throw new UnsupportedOperationException(INSTANTIATION_ERROR);
  }

  public static Object[] £(Object... any) {
    return any;
  }
}
