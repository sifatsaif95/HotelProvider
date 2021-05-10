package com.britishbroadcast.mycontentprovider.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HotelGuests")
data class HotelGuest(
    @ColumnInfo(name = "GuestName")
    val guestName: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "GuestID")
    val guestID: Int
) {
    constructor(guestName: String) : this(guestName, 0)
}