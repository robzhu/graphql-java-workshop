package com.howtographql.hackernews;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {
  private final LinkRepository linkRepository = new LinkRepository();
  private final UserRepository userRepository = new UserRepository();
  private final VoteRepository voteRepository = new VoteRepository();

  public Link createLink(String url, String description, DataFetchingEnvironment env) {
    AuthContext context = env.getContext();

    Link newLink = new Link();
    newLink.setId(IDUtil.generateID());
    newLink.setUrl(url);
    newLink.setDescription(description);
    newLink.setPostedByUserId(context.getUser().id);

    linkRepository.saveLink(newLink);
    return newLink;
  }

  public User createUser(NewUserInput input) throws Exception {
    return userRepository.createNewUser(input);
  }

  public SigninPayload signinUser(AuthData auth) throws IllegalAccessException, GraphQLException {
    User user = userRepository.findByUsername(auth.username);
    if (user == null)
      throw new GraphQLException("that user does not exist");

    if (user.getPassword().equals(auth.password)) {
      return new SigninPayload(user.getId(), user);
    }

    throw new GraphQLException("Invalid credentials");
  }

  public Vote createVote(String linkId, String userId) {
    ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
    return voteRepository.saveVote(new Vote(now, userId, linkId));
  }
}