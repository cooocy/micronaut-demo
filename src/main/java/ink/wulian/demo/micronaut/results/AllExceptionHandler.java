package ink.wulian.demo.micronaut.results;

import cn.hutool.core.exceptions.ExceptionUtil;
import ink.wulian.demo.micronaut.utils.HttpContextUtils;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.Optional;

@Slf4j
@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class AllExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {

    private static final String NOT_GOT_ERROR_MESSAGE = "can not got error message";

    @Override
    public HttpResponse handle(HttpRequest request, Exception exception) {
        Result result;
        if (exception instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) exception;
            result = serviceException.convert();
        } else {
            result = Result.error(ResultEnum.error, Optional.ofNullable(ExceptionUtil.getRootCauseMessage(exception)).orElse(NOT_GOT_ERROR_MESSAGE));
        }
        if (HttpMethod.OPTIONS != request.getMethod()) {
            log.error("<Request> RemoteAddress: {}, Path: {}, Duration: {}", HttpContextUtils.getIp(request), request.getPath(), HttpContextUtils.getDuration(request));
        }
        return HttpResponse.serverError().body(result);
    }

}