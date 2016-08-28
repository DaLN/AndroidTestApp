package com.example.nelson.data.entity;

/**
 * Created by Nelson on 14/08/2016.
 */
public abstract class TestResponse {

  private static final long STALE_MILLISECONDS = 60 * 60 * 1000; // Data is stale after 1 hour.
  final long timestamp;

  public TestResponse() {
    this.timestamp = System.currentTimeMillis();
  }

  public boolean isUpToDate() {
    return System.currentTimeMillis() - timestamp < STALE_MILLISECONDS;
  }
}

