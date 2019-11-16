package com.howtographql.hackernews;

public final class SigninPayload {
  public String token;
  public User user;

  public SigninPayload(String token, User user) {
    this.token = token;
    this.user = user;
  }
}