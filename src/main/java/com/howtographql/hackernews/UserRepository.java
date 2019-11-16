package com.howtographql.hackernews;

public class UserRepository extends Repository<User> {
  public UserRepository() {
    super(User.class);
  }

  public User findByEmail(String email) {
    return super.firstOrDefaultWhereAttributeEquals(User.EmailAttribute, email);
  }

  public User findById(String id) {
    return super.findById(id);
  }

  public User createNewUser(NewUserInput input) {
    // TODO: check for duplicate username/email/password strength
    User newUser = new User(input);
    mapper.save(newUser);
    return newUser;
  }

  public User findByUsername(String username) {
    return super.firstOrDefaultWhereAttributeEquals("username", username);
  }
}