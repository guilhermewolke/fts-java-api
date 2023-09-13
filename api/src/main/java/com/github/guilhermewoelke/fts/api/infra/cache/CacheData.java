package com.github.guilhermewoelke.fts.api.infra.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@Accessors(chain = true)
@RedisHash(value = "fts_bookshelf", timeToLive = 10L)
public class CacheData {
    @Id
    private String key;

    @Indexed
    private String value;
}
