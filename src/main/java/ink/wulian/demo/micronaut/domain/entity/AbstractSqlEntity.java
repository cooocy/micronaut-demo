package ink.wulian.demo.micronaut.domain.entity;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractSqlEntity {

    private String id;

    private LocalDateTime insertTime;

    public void setInsertFields() {
        id = String.valueOf(IdUtil.getSnowflake(0, 0).nextId());
        insertTime = LocalDateTime.now();
    }

}
