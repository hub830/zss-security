package com.zss.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import com.fasterxml.jackson.annotation.JsonView;
import com.zss.validator.MyConstraint;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

  public interface UserSimpleView {
  };

  public interface UserDetailView extends UserSimpleView {
  };

  
  @JsonView(UserSimpleView.class)
  private Long id;
  
  @MyConstraint(message="����һ������")
  @JsonView(UserSimpleView.class)
  private String username;

  @NotBlank(message="���벻��Ϊ��")
  @JsonView(UserDetailView.class)
  private String password;

  @Past(message="���ձ��� Ϊ��ȥ��ʱ��")
  @JsonView(UserDetailView.class)
  private Date birthday;
}
