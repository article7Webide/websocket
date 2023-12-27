package article.WebIde.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private Long accessTokenExpiresIn;
    private String refreshToken;
    private String token;

    // 수정: RefreshToken이 null이 아니라면 refreshToken을 반환하도록 변경
    public String getRefreshToken(){
        return refreshToken != null ? refreshToken : "";
    }

    public String getToken() {
        return token != null ? token : "";
    }
}


