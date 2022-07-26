package com.tkluza.spring.efficientunittests.business.place.test

import com.tkluza.spring.efficientunittests.business.place.domain.model.PlaceEntity
import com.tkluza.spring.efficientunittests.business.place.domain.model.SeatEntity
import com.tkluza.spring.efficientunittests.common.test.TestDataContext
import com.tkluza.spring.efficientunittests.common.test.factory.TestFactory

object PlaceTestFactory {

    private const val COLUMN_NAME = "Name"
    private const val COLUMN_ADDRESS = "Address"
    private const val COLUMN_PLACE = "Place"
    private const val COLUMN_SECTION = "Section"
    private const val COLUMN_ROW_NUMBER = "Row number"
    private const val COLUMN_SEAT_NUMBER = "Seat number"
    private const val COLUMN_IS_STANDING = "Is standing"

    /**
     * Structure for [PlaceEntity] in tests
     *
     * | KEY | Name | Address |
     */
    fun savePlaces(placeRows: Array<String>, testDataContext: TestDataContext) {
        TestFactory.saveTestData(
            rows = placeRows,
            testDataContext = testDataContext,
            singleEntityCreator = this::createPlace
        )
    }

    private fun createPlace(userRow: Map<String, String>, testDataContext: TestDataContext): PlaceEntity =
        PlaceEntity(
            name = userRow[COLUMN_NAME] ?: "",
            address = userRow[COLUMN_ADDRESS] ?: "",
        )

    /**
     * Structure for [PlaceEntity] in tests
     *
     * | KEY | Place | Section | Row number | Seat number | Is standing |
     */
    fun saveSeats(placeRows: Array<String>, testDataContext: TestDataContext) {
        TestFactory.saveTestData(
            rows = placeRows,
            testDataContext = testDataContext,
            singleEntityCreator = this::createSeat
        )
    }

    private fun createSeat(userRow: Map<String, String>, testDataContext: TestDataContext): SeatEntity {
        val placeEntity = testDataContext[userRow.getValue(COLUMN_PLACE), PlaceEntity::class.java]!!
        return SeatEntity(
            placeId = placeEntity.id,
            placeEntity = placeEntity,
            section = userRow[COLUMN_SECTION],
            rowNumber = userRow[COLUMN_ROW_NUMBER]?.run { if (isNotBlank()) toInt() else null },
            seatNumber = userRow[COLUMN_SEAT_NUMBER]?.toInt() ?: 0,
            isStanding = userRow[COLUMN_IS_STANDING].toBoolean()
        )
    }
}