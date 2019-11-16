package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class VoteResolver implements GraphQLResolver<Vote> {
  private final LinkRepository linkRepository = new LinkRepository();
  private final UserRepository userRepository = new UserRepository();

  public User user(Vote vote) {
    System.out.println("here");
    try {
      User user = userRepository.findById(vote.getUserId());
      System.out.println(user.id);
      return user;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public Link link(Vote vote) {
    return linkRepository.findById(vote.getLinkId());
  }
}