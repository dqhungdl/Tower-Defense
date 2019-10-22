package com.uet.towerdefense.worker.repository.base;

import com.uet.towerdefense.common.entity.base.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<T extends BaseEntity<Tid>, Tid extends Serializable> {
}
