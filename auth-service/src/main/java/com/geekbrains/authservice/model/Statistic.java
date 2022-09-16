package com.geekbrains.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@RedisHash("statistic")
public class Statistic implements Serializable {

    private static final long serialVersionUID = 22910212333555690L;

    @Id
    private String id;
    @Indexed
    private String className;
    @Indexed
    private String method;

    private List<Long> time;

    private Integer size = 10;

    public Statistic(String className, String method, List<Long> time) {
        this.className = className;
        this.method = method;
        this.time = time;
    }
}
