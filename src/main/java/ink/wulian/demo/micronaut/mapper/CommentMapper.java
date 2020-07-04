package ink.wulian.demo.micronaut.mapper;

import ink.wulian.demo.micronaut.domain.entity.Comment;
import lombok.NonNull;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    @Insert("insert into comment (id, insert_time, article_id, nickname, email, body, delete_flag) values " +
            "(#{id}, #{insertTime}, #{articleId}, #{nickname}, #{email}, #{body}, 0)")
    int insert(@NonNull Comment comment);

    @Results(id = "comment", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "insert_time", property = "insertTime"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "email", property = "email"),
            @Result(column = "body", property = "body")})

    @Select("select * from comment where article_id = #{articleId} and delete_flag = 0")
    List<Comment> selectListByArticleId(@NonNull String articleId);

}
