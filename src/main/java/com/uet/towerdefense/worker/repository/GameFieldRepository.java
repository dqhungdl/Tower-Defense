package com.uet.towerdefense.worker.repository;

import com.uet.towerdefense.common.entity.GameFieldImpl;
import com.uet.towerdefense.worker.repository.base.BaseMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameFieldRepository extends BaseMongoRepository<GameFieldImpl, Long> {
}
