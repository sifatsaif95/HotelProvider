package com.britishbroadcast.mycontentprovider

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.britishbroadcast.mycontentprovider.model.HotelDB
import com.britishbroadcast.mycontentprovider.model.data.HotelGuest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readFromContentProvider()
    }

    private fun readFromContentProvider() {
        val contentResolver = contentResolver
//com.britishbroadcast.mycontentprovider.Hotel/HotelGuest/2
        val uri =
            Uri.parse("content://com.britishbroadcast.mycontentprovider.Hotel/HotelGuests") //Get all guests
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        while (cursor?.moveToNext() == true)
            Log.d("TAG_X", cursor.getString(cursor.getColumnIndex("GuestName")))
        cursor?.close()
    }
}