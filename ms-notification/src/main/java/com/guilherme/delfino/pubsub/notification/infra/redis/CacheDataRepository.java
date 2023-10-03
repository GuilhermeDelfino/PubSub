package com.guilherme.delfino.pubsub.notification.infra.redis;

import org.springframework.data.repository.CrudRepository;

public interface CacheDataRepository extends CrudRepository<CacheData, String> {
}
