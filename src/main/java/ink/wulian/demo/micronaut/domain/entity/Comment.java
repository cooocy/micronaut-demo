package ink.wulian.demo.micronaut.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Comment extends AbstractSqlEntity {

    private String articleId;

    private String nickname;

    private String email;

    private String body;

}
