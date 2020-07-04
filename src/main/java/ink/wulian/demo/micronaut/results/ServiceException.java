package ink.wulian.demo.micronaut.results;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ResultEnum resultEnum;

    private String message;

    public ServiceException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    public ServiceException(ResultEnum resultEnum, String message) {
        this.resultEnum = resultEnum;
        this.message = message;
    }

    public Result<String> convert() {
        if (StrUtil.isEmpty(message)) {
            return Result.error(resultEnum);
        } else {
            return Result.error(resultEnum, message);
        }
    }

}
