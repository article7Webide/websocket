package article.WebIde.api.util;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}
