package com.privatesecuredata.eventplanner.pages;

import org.apache.tapestry5.annotations.PageActivationContext;

public class Rooms
{
  @PageActivationContext
  private String learn;


  public String getLearn() {
    return learn;
  }

  public void setLearn(String learn) {
    this.learn = learn;
  }
}
