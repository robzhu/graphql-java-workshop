package com.howtographql.hackernews;

import java.time.ZonedDateTime;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class ZonedDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {

  @Override
  public String convert(final ZonedDateTime time) {
    return time.toString();
  }

  @Override
  public ZonedDateTime unconvert(final String stringValue) {
    return ZonedDateTime.parse(stringValue);
  }
}