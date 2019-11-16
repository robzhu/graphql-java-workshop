package com.howtographql.hackernews;

import java.time.ZonedDateTime;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "HNVotes")
public class Vote {
  private String id;
  private ZonedDateTime createdAt;
  private String userId;
  private String linkId;

  public Vote() {
  }

  public Vote(ZonedDateTime createdAt, String userId, String linkId) {
    this.createdAt = createdAt;
    this.userId = userId;
    this.linkId = linkId;
    this.id = IDUtil.generateID();
  }

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBAttribute(attributeName = "createdAt")
  @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @DynamoDBAttribute(attributeName = "userId")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @DynamoDBAttribute(attributeName = "linkId")
  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }
}