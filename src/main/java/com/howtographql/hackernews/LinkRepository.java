package com.howtographql.hackernews;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class LinkRepository extends Repository<Link> {
  public LinkRepository() {
    super(Link.class);
  }

  public Link findById(String id) {
    return mapper.load(Link.class, id);
  }

  public List<Link> getAllLinks() {
    return mapper.scan(Link.class, new DynamoDBScanExpression());
  }

  public void saveLink(Link link) {
    mapper.save(link);
  }

  public Link findByUrl(String url) {
    try {
      return super.firstOrDefaultWhereAttributeEquals("address", url);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}