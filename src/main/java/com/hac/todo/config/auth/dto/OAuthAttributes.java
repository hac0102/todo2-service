package com.hac.todo.config.auth.dto;

import com.hac.todo.domain.user.Role;
import com.hac.todo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Slf4j
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String joinType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String joinType){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.joinType = joinType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes, registrationId);
        }
        return ofGoogle(userNameAttributeName, attributes, registrationId);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        log.info("네이버 로그인 정보 response :: {}",  response);

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .joinType(registrationId)
                .build();

    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
        log.info("구글 로그인 정보 response :: {}", attributes);
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .joinType(registrationId)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .joinType(joinType)
                .build();
    }

}
