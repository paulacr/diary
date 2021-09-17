package com.paulacr.dreamdiary.ui.views

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulacr.dreamdiary.data.database.AppDatabase
import com.paulacr.dreamdiary.data.database.DreamDao
import dagger.hilt.android.qualifiers.ApplicationContext
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MonthSelectorViewTest : TestCase() {

    private lateinit var dreamDao: DreamDao
    private lateinit var db: AppDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dreamDao = db.dreamDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}