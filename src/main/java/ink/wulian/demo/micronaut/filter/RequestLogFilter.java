package ink.wulian.demo.micronaut.filter;

import ink.wulian.demo.micronaut.utils.HttpContextUtils;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import static io.micronaut.http.HttpMethod.*;

@Slf4j
@Filter(value = Filter.MATCH_ALL_PATTERN, methods = {GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH, CUSTOM})
public class RequestLogFilter extends OncePerRequestHttpServerFilter {

    @Override
    protected Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
        HttpContextUtils.beginTiming(request);
        return Publishers.map(chain.proceed(request), response -> {
            log.info("<Request> RemoteAddress: {}, Path: {}, Duration: {}", HttpContextUtils.getIp(request), request.getPath(), HttpContextUtils.getDuration(request));
            return response;
        });
    }

    @Override
    public int getOrder() {
        return 10;
    }

}
