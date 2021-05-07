package com.odod.domain.user;

import com.odod.common.BaseTimeEntity;
import com.odod.dto.UserResponseDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long idx;

  @Column
  private String userId;

  @Column
  private String password;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private String useYn;

  public UserResponseDto toResponseDto(User user) {
    return UserResponseDto.builder().userId(user.getUserId())
        .name(user.getName()).email(user.getEmail()).build();
  }
}
