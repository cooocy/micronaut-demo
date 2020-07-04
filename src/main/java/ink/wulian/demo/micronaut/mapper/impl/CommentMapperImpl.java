package ink.wulian.demo.micronaut.mapper.impl;

import ink.wulian.demo.micronaut.domain.entity.Comment;
import ink.wulian.demo.micronaut.mapper.CommentMapper;
import lombok.NonNull;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommentMapperImpl implements CommentMapper {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    private CommentMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CommentMapper.class);
    }

    @Override
    public int insert(@NonNull Comment comment) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int rows = getMapper(sqlSession).insert(comment);
            sqlSession.commit();
            return rows;
        }
    }

    @Override
    public List<Comment> selectListByArticleId(@NonNull String articleId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getMapper(sqlSession).selectListByArticleId(articleId);
        }
    }

}
