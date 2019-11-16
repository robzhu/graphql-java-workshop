package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class SigninResolver implements GraphQLResolver<SigninPayload> {
  public String getToken(SigninPayload payload) {
    return payload.token;
  }

  public User user(SigninPayload payload) {
    return payload.user;
  }
}