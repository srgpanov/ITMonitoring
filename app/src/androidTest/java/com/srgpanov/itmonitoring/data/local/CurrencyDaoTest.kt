package com.srgpanov.itmonitoring.data.local

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.srgpanov.itmonitoring.data.model.ValCurs
import com.srgpanov.itmonitoring.data.model.Valute
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    lateinit var db:CurrencyDataBase
    lateinit var dao:CurrencyDao

    @Before
    fun setUp() {
        db= Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CurrencyDataBase::class.java
        ).build()
        dao=db.currencyDao()
    }

    @After
    fun tearDown() {
        db.close()
    }



    @Test
    fun testInsertAndGetValCurs() {
        val valute =Valute(
            id = "",
            charCode = "",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = "05.05.2020"
        )
        val valute2 =Valute(
            id = "",
            charCode = "",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = "05.05.2020"
        )
        val valCurs = ValCurs(
            name = "12", date = "05.05.2020", valute = listOf(valute,valute2)
        )
        dao.insertValCurs(valCurs)
        val testObserver = TestObserver<ValCurs>()
        val list: Single<ValCurs> = dao.getValCurs().flatMap { Single.just(it.first()) }
        list.subscribe(testObserver)
        testObserver.assertValue(valCurs)
    }
    @Test(expected = SQLiteConstraintException::class)
    fun testInsertWrongKey() {
        val valute =Valute(
            id = "",
            charCode = "",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = "05.05.2020"
        )
        val valute2 =Valute(
            id = "",
            charCode = "",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = "05.05.2020"
        )
        val valCurs = ValCurs(
            name = "12", date = "06.05.2020", valute = listOf(valute,valute2)
        )
        dao.insertValCurs(valCurs)
        val testObserver = TestObserver<ValCurs>()
        val list: Single<ValCurs> = dao.getValCurs().flatMap { Single.just(it.first()) }
        list.subscribe(testObserver)
        testObserver.assertValue(valCurs)
    }

}