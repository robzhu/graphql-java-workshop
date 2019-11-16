package com.howtographql.hackernews;

import java.util.UUID;

public final class IDUtil {
  public static String generateID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}