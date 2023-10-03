package com.guilherme.delfino.pubsub.notification.domain.service;

public interface CacheService {
    String getItem(String key);
    String setItem(String key, String item);
    void removeItem(String key);
    String updateItem(String key, String item);
}
