package ink.wulian.demo.micronaut.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    ok("10000", "ok"),
    error("10010", "error"),
    paramIllegal("10040", "param illegal"),
    ;

    private final String code;

    private final String message;

}
