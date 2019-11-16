package com.howtographql.hackernews;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "HNLinks")
public class Link {
  private String id;
  private String url;
  private String description;
  private String postedByUserId;

  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @DynamoDBAttribute(attributeName = "address")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @DynamoDBAttribute(attributeName = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @DynamoDBAttribute(attributeName = "postedByUserId")
  public String getPostedByUserId() {
    return postedByUserId;
  }

  public void setPostedByUserId(String postedByUserId) {
    this.postedByUserId = postedByUserId;
  }
}