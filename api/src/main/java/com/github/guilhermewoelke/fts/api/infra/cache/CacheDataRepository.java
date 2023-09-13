package com.github.guilhermewoelke.fts.api.infra.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDataRepository extends CrudRepository<CacheData, String> {
}
