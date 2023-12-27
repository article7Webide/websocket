package article.WebIde.api.dto;

import article.WebIde.api.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String email;

    public MemberResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getId(), member.getEmail());
    }
}
