package com.tkluza.spring.efficientunittests.common.test.factory

import com.google.common.collect.Table
import com.tkluza.spring.efficientunittests.common.model.EntityWithId
import com.tkluza.spring.efficientunittests.common.test.TestContext
import com.tkluza.spring.efficientunittests.common.test.factory.TestTable.COLUMN_KEY

typealias TestFactoryMapper<T> = (Map<String, String>, TestContext) -> T

object TestFactory {

    fun createTestData(rows: Array<String>, testContext: TestContext, testFactoryMapper: TestFactoryMapper<EntityWithId<*>>) {
        val testTable: Table<String, String, String> = TestTable.mapToTable(rows)
        for (row in testTable.rowMap().values) {
            testContext.save(
                key = row[COLUMN_KEY] ?: "",
                entity = testFactoryMapper(row, testContext)
            )
        }
    }
}