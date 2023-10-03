package com.guilherme.delfino.pubsub.notification.infra.service;

import com.guilherme.delfino.pubsub.notification.domain.service.CacheService;
import com.guilherme.delfino.pubsub.notification.infra.redis.CacheData;
import com.guilherme.delfino.pubsub.notification.infra.redis.CacheDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RedisCacheService implements CacheService {
    private final CacheDataRepository repository;
    @Override
    public String getItem(String key) {
        Optional<CacheData> item = repository.findById(key);
        return item.map(CacheData::getValue).orElse(null);
    }

    @Override
    public String setItem(String key, String item) {
        CacheData save = repository.save(new CacheData(key, item));
        return save.getValue();
    }

    @Override
    public void removeItem(String key) {
        repository.deleteById(key);
    }

    @Override
    public String updateItem(String key, String item) {
        CacheData save = repository.save(new CacheData(key, item));
        return save.getValue();
    }
}
