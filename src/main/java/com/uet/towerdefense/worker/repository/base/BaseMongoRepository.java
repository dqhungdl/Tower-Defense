package com.uet.towerdefense.worker.repository.base;

import com.uet.towerdefense.common.entity.base.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface BaseMongoRepository<T extends BaseEntity<Tid>, Tid extends Serializable> extends BaseRepository, MongoRepository<T, Tid> {
}
