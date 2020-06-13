package com.srgpanov.itmonitoring.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Parcelize
@Entity(tableName ="Valute",
    foreignKeys = [ForeignKey(entity = DayCurs::class,
        parentColumns = ["date"],
        childColumns = ["date"],
        deferred = true,
        onDelete = ForeignKey.CASCADE
    )] )
@Root(name = "Valute", strict = false)
data class Valute @JvmOverloads constructor(
    @ColumnInfo(name="id")
    @field:Attribute(name = "ID",required = false)
    var id: String="",

    @ColumnInfo(name="charCode")
    @field:Element(name = "CharCode",required = false)
    var charCode: String="",

    @ColumnInfo(name="value")
    @field:Element(name = "Value",required = false)
    var value: String="",

    @ColumnInfo(name="nominal")
    @field:Element(name = "Nominal",required = false)
    var nominal: Int=1,

    @ColumnInfo(name="numCode")
    @field:Element(name = "NumCode",required = false)
    var numCode: Int=0,

    @ColumnInfo(name="name")
    @field:Element(name = "Name",required = false)
    var name: String="",

    @ColumnInfo(name = "date")
    var date:String=""
    ) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var roomId:Long=0
    fun value(): Float? {
        return value.replace(',','.').toFloatOrNull()
    }
}