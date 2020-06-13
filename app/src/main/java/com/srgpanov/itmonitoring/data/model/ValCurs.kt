package com.srgpanov.itmonitoring.data.model

import android.util.Log
import androidx.room.*
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@Root(name = "ValCurs", strict = false)
data class ValCurs @JvmOverloads constructor(

    @field:Attribute(name = "name",required = false)
    var name: String="",

    @field:Attribute(name = "Date",required = false)
    var date: String="",
    @Relation(parentColumn = "date", entityColumn = "date")
    @field:ElementList(name = "Valute", inline = true)
    var valute: List<Valute> = mutableListOf()){
    fun toDayCurs():DayCurs{
        return DayCurs(name,date,timeStamp(date))
    }
    fun timeStamp(date: String):Long{
        Log.d("DayCurs", "timeStamp:  ")
        val dateToday = try {
            SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault()).parse(date)
        } catch (e: ParseException) {
            Log.e("DayCurs", "$e " )
            Date()
        }
        return dateToday.time
    }
}







