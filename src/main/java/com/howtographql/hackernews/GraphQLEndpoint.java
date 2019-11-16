package com.howtographql.hackernews;

import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  public GraphQLEndpoint() {
    super(buildSchema());
  }

  private static GraphQLSchema buildSchema() {
    LinkRepository linkRepository = new LinkRepository();
    UserRepository userRepository = new UserRepository();
    return SchemaParser
        .newParser().file("schema.graphqls").resolvers(new Query(linkRepository), new Mutation(), new SigninResolver(),
            new LinkResolver(userRepository), new VoteResolver())
        .scalars(Scalars.dateTime).build().makeExecutableSchema();
  }

  @Override
  protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
    return errors;
  }

  @Override
  protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
    UserRepository userRepository = new UserRepository();
    User user = request.map(req -> req.getHeader("Authorization")).filter(id -> !id.isEmpty())
        .map(id -> id.replace("Bearer ", "")).map(userRepository::findById).orElse(null);
    return new AuthContext(user, request, response);
  }
}