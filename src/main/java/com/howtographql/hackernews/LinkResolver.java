package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class LinkResolver implements GraphQLResolver<Link> {

  private final UserRepository userRepository;

  public LinkResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User postedBy(Link link) {
    if (link.getPostedByUserId() == null) {
      return null;
    }
    return userRepository.findById(link.getPostedByUserId());
  }
}