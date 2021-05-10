package com.britishbroadcast.mycontentprovider.model.data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDAO {

    @Query("SELECT * FROM HotelGuests WHERE GuestID = :guestID")
    fun getSingleGuest(guestID: Int): Cursor

    @Query("SELECT * FROM HotelGuests")
    fun getAllGuests(): Cursor

    @Insert
    fun insertHotelGuest(guest: HotelGuest)
}