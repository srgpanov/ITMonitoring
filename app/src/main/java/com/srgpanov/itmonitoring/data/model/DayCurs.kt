package com.srgpanov.itmonitoring.data.model

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "ValCurs")
data class DayCurs(
    @ColumnInfo(name="name")
    var name: String="",

    @PrimaryKey
    @ColumnInfo(name="date")
    var date: String="",
    val timeStamp:Long
) {

}