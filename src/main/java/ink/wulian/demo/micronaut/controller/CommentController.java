package ink.wulian.demo.micronaut.controller;

import cn.hutool.core.util.StrUtil;
import ink.wulian.demo.micronaut.service.CommentService;
import ink.wulian.demo.micronaut.domain.ro.AddComment;
import ink.wulian.demo.micronaut.domain.vo.CommentInfo;
import ink.wulian.demo.micronaut.results.Result;
import ink.wulian.demo.micronaut.results.ResultEnum;
import ink.wulian.demo.micronaut.results.ServiceException;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import java.util.List;

@Controller("/comment")
public class CommentController extends BaseController {

    @Inject
    private CommentService commentService;

    @Post
    public Result add(@Body AddComment body) {
        body.checkParams();
        commentService.add(body.getArticleId(), body.getNickname(), body.getEmail(), body.getBody());
        return Result.ok();
    }

    @Get("list-by-article-id")
    public Result<List<CommentInfo>> listByArticleId(String articleId) {
        if (StrUtil.isEmpty(articleId)) {
            throw new ServiceException(ResultEnum.paramIllegal, "articleId is empty!");
        }
        return Result.ok(commentService.listByArticleId(articleId));
    }

}
