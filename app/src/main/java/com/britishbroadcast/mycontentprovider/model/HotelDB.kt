package com.britishbroadcast.mycontentprovider.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.britishbroadcast.mycontentprovider.model.data.HotelDAO
import com.britishbroadcast.mycontentprovider.model.data.HotelGuest

@Database(entities = [HotelGuest::class], version = 1)
abstract class HotelDB: RoomDatabase() {
    abstract fun getDAO(): HotelDAO
}
