package com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.repository

import com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.model.SubscriberModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaSubscriberRepository : JpaRepository<SubscriberModel, UUID>