package ink.wulian.demo.micronaut.domain.vo;

import ink.wulian.demo.micronaut.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentInfo {

    private String id;

    private LocalDateTime insertTime;

    private String articleId;

    private String nickname;

    private String email;

    private String body;

    public static CommentInfo newInstance(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.id = comment.getId();
        commentInfo.insertTime = comment.getInsertTime();
        commentInfo.articleId = comment.getArticleId();
        commentInfo.nickname = comment.getNickname();
        commentInfo.email = comment.getEmail();
        commentInfo.body = comment.getBody();
        return commentInfo;
    }

}
