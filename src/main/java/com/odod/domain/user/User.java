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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseTimeEntity {

  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(columnDefinition = "BINARY(16)")
  @Id // primary key
  private UUID idx;

  @Column
  private String userId;

  @Column
  private String password;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private Character useYn;

  public User deleteUser() {
    this.useYn = 'Y';

    return this;
  }

  public User updatePassword(String password) {
    this.password = password;

    return this;
  }

  public UserResponseDto toResponseDto(User user) {

    return UserResponseDto.builder().idx(user.getIdx()).userId(user.getUserId())
        .name(user.getName()).email(user.getEmail()).build();
  }
}
