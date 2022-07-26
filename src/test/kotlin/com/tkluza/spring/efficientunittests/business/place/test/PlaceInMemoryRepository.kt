package com.tkluza.spring.efficientunittests.business.place.test

import com.tkluza.spring.efficientunittests.business.place.domain.model.PlaceEntity
import com.tkluza.spring.efficientunittests.common.test.id.IdGenerator
import com.tkluza.spring.efficientunittests.common.test.inmemory.InMemoryTestDataRepository

class PlaceInMemoryRepository(
    idGenerator: IdGenerator<Long>
) : InMemoryTestDataRepository<PlaceEntity, Long>(
    entityClass = PlaceEntity::class.java,
    idGenerator = idGenerator,
    keyPrefix = "P"
)