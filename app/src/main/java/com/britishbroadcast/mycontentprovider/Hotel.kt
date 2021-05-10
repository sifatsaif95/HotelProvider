package com.britishbroadcast.mycontentprovider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.britishbroadcast.mycontentprovider.model.HotelDB

class Hotel : ContentProvider() {

    //for it to be accessible by other applications -> link -> authority
    val authority = "com.britishbroadcast.mycontentprovider.Hotel"
    //content://user_dictionary/words
    //content://com.britishbroadcast.mycontentprovider.Hotel/1/

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private val SINGLE_GUEST = 0
    private val ALL_GUESTS = 1

    private lateinit var hotelDatabase: HotelDB

    override fun onCreate(): Boolean {

        uriMatcher.apply {
            addURI(
                authority,
                "HotelGuests/#",
                SINGLE_GUEST
            )//com.britishbroadcast.mycontentprovider.Hotel/HotelGuest/2 ->
            addURI(authority, "HotelGuests", ALL_GUESTS)
        }
        context?.let {

            hotelDatabase = Room.databaseBuilder(
                it,
                HotelDB::class.java,
                "Hotel.db"
            ).allowMainThreadQueries()
                .build()
        }
        return this::hotelDatabase.isInitialized
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?, //{"GuestName", "GuestID"}
        selection: String?,
        selectionArgs: Array<out String>?,//{"GuestID"}
        sortOrder: String?
    ): Cursor? {

        Log.d("TAG_X", "Query called...${uri.pathSegments} ${Thread.currentThread().name}")
        return when (uriMatcher.match(uri)) {
            ALL_GUESTS -> {
                Log.d("TAG_X", "Query called...All guests")
                val x = hotelDatabase.getDAO().getAllGuests()
                x
            }
            SINGLE_GUEST -> {
                val id = Integer.parseInt(uri.pathSegments[1])
                hotelDatabase.getDAO().getSingleGuest(id)
            }
            else -> null
        }
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String {
        return ""
    }
}