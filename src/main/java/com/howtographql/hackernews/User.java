package com.howtographql.hackernews;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = User.TableName)
public class User {
  public static final String TableName = "HNUsers";

  public String id;
  public String username;
  public String email;
  public String password;

  public User() {
    this(null);
  }

  public User(NewUserInput input) {
    if (input == null)
      return;
    this.username = input.username;
    this.email = input.email;
    this.password = input.password;
    this.id = IDUtil.generateID();
  }

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBAttribute(attributeName = "username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public static final String EmailAttribute = "email";

  @DynamoDBAttribute(attributeName = EmailAttribute)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @DynamoDBAttribute(attributeName = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
