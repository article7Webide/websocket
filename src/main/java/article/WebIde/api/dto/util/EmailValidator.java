package article.WebIde.api.dto.util;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    public boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
