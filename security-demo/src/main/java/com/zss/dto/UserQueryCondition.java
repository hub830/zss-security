package com.zss.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserQueryCondition {

//  @MyConstraint(message="这是一个测试")
  private String username;
  
  private int age;
  
  private int ageTo;
  
  private String xxx;
}
