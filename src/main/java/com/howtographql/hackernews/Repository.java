package com.howtographql.hackernews;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public abstract class Repository<T> {
  protected DynamoDBMapper mapper;
  private Class<T> clazz;

  protected Repository(Class<T> clazz) {
    this.clazz = clazz;
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    mapper = new DynamoDBMapper(client);
  }

  protected List<T> findItemsWhereAttributeEquals(String attribute, String value) {
    try {
      Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
      eav.put(":val1", new AttributeValue().withS(value));

      return mapper.scan(clazz, new DynamoDBScanExpression()
          .withFilterExpression(String.format("%s = :val1", attribute)).withExpressionAttributeValues(eav));
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return new ArrayList<T>();
    }
  }

  // helper function for scanning for a single item with attribute equals.
  protected T firstOrDefaultWhereAttributeEquals(String attribute, String value) {
    return findItemsWhereAttributeEquals(attribute, value).stream().findFirst().orElse(null);
  }

  protected T findById(String id) {
    return mapper.load(clazz, id);
  }
}