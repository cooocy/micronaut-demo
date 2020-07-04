package ink.wulian.demo.micronaut.controller;

import ink.wulian.demo.micronaut.utils.HttpContextUtils;
import ink.wulian.demo.micronaut.results.Result;
import ink.wulian.demo.micronaut.results.ResultEnum;
import ink.wulian.demo.micronaut.results.ServiceException;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Controller("/health")
public class HealthController extends BaseController {

    @Value("${micronaut.application.name}")
    private String appName;

    @Value("${app.info}")
    private String appInfo;

    @Get("/check")
    public Result<Health> check(HttpRequest request) {
        Health health = new Health();
        health.serverName = appName;
        health.serverTime = LocalDateTime.now();
        health.remoteIP = HttpContextUtils.getIp(request);
        health.userAgent = HttpContextUtils.getUserAgent(request);
        health.serverInfo = appInfo;
        return Result.ok(health);
    }

    @Get("/error")
    public void error() {
        throw new ServiceException(ResultEnum.error);
    }

    @Getter
    public static class Health {

        private String serverName;

        private String serverInfo;

        private LocalDateTime serverTime;

        private String userAgent;

        private String remoteIP;

    }

}
