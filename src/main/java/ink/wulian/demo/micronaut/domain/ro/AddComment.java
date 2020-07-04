package ink.wulian.demo.micronaut.domain.ro;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import ink.wulian.demo.micronaut.results.ResultEnum;
import ink.wulian.demo.micronaut.results.ServiceException;
import lombok.Data;

@Data
public class AddComment {

    private String articleId;

    private String nickname;

    private String email;

    private String body;

    public void checkParams() {
        if (Validator.isEmpty(articleId)) {
            throw new ServiceException(ResultEnum.paramIllegal, "articleId is empty!");
        }
        if (StrUtil.isEmpty(nickname)) {
            throw new ServiceException(ResultEnum.paramIllegal, "nickname is empty!");
        }
        if (StrUtil.isEmpty(email)) {
            throw new ServiceException(ResultEnum.paramIllegal, "email is empty!");
        }
        if (StrUtil.isEmpty(body)) {
            throw new ServiceException(ResultEnum.paramIllegal, "body is empty!");
        }
    }

}
