package ink.wulian.demo.micronaut.service;

import ink.wulian.demo.micronaut.domain.entity.Comment;
import ink.wulian.demo.micronaut.domain.vo.CommentInfo;
import ink.wulian.demo.micronaut.mapper.CommentMapper;
import lombok.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CommentService {

    @Inject
    private CommentMapper commentMapper;

    public void add(@NonNull String articleId, @NonNull String nickname, @NonNull String email, @NonNull String body) {
        Comment comment = new Comment()
                .setArticleId(articleId)
                .setNickname(nickname)
                .setEmail(email)
                .setBody(body);
        comment.setInsertFields();
        commentMapper.insert(comment);
    }

    public List<CommentInfo> listByArticleId(@NonNull String articleId) {
        List<Comment> comments = commentMapper.selectListByArticleId(articleId);
        return comments.stream().map(CommentInfo::newInstance).collect(Collectors.toList());
    }

}
