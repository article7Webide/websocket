package article.WebIde.api.dto.util;

import article.WebIde.api.entity.Member;
import article.WebIde.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    private static MemberRepository memberRepository;
    private static PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordValidator(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        PasswordValidator.memberRepository = memberRepository;
        PasswordValidator.passwordEncoder = passwordEncoder;
    }

    public static void validatePassword(String password) {
        // 비밀번호가 8~16자의 영문 대소문자, 숫자, 특수문자를 모두 포함하는지 확인하는 정규 표현식
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$";
        if (!password.matches(regex)) {
            throw new InvalidPasswordException("비밀번호가 8~16자의 영문 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.");
        }
    }

    public static boolean isPasswordMatchingEmail(String email, String password) {
        try {
            // 데이터베이스에서 해당 이메일을 가진 사용자를 찾아옴
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new InvalidPasswordException("이메일이 올바르지 않습니다."));

            // 저장된 비밀번호와 입력된 비밀번호를 비교
            return passwordEncoder.matches(password, member.getPassword());
        } catch (InvalidPasswordException e) {
            // 예외 로깅 추가
            e.printStackTrace();
            throw e;
        }
    }
}