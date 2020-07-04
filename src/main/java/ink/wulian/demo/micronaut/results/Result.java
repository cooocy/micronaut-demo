package ink.wulian.demo.micronaut.results;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private Boolean success;

    private String code;

    private String message;

    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = ok();
        result.data = data;
        return result;
    }

    public static Result ok() {
        Result result = new Result();
        result.success = true;
        result.code = ResultEnum.ok.getCode();
        result.message = ResultEnum.ok.getMessage();
        return result;
    }

    protected static Result<String> error(@NonNull ResultEnum resultEnum) {
        Result<String> result = new Result();
        result.success = false;
        result.code = resultEnum.getCode();
        result.message = resultEnum.getMessage();
        return result;
    }

    protected static Result<String> error(@NonNull ResultEnum resultEnum, @NonNull String message) {
        Result<String> error = error(resultEnum);
        error.message = message;
        return error;
    }

}
