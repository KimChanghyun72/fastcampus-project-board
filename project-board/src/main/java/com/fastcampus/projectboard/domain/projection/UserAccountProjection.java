package com.fastcampus.projectboard.domain.projection;

import com.fastcampus.projectboard.domain.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "withoutPassword", types= UserAccount.class)     // 민감정보인 패스워드는 오픈하지 않음.
public interface UserAccountProjection {
    String getUserId();
    String getEmail();
    String getNickName();
    String getMemo();
    LocalDateTime getCreatedAt();
    String getCreatedBy(); // 생성자
    LocalDateTime getModifiedAt(); // 수정일시
    String getModifiedBy(); // 수정자
}
