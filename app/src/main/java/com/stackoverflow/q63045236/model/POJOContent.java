package com.stackoverflow.q63045236.model;

import com.google.gson.annotations.SerializedName;

public class POJOContent {

  @SerializedName("ab")
  public String content;

  @SerializedName("id")
  public String id;

  @SerializedName("key")
  public String key;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override public String toString() {
    return "POJOContent{" +
        "content='" + content + '\'' +
        ", id='" + id + '\'' +
        ", key='" + key + '\'' +
        '}';
  }
}
