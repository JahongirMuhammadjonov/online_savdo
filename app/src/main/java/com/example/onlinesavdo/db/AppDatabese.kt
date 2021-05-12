package com.example.onlinesavdo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlinesavdo.Model.CategoryModel
import com.example.onlinesavdo.Model.ProductModel

@Database(entities = [CategoryModel::class, ProductModel::class], version = 1)
abstract  class AppDatabese: RoomDatabase(){
    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryDao(): CategoryDao

     companion object {
        var INSTANCE: AppDatabese? = null
        fun initDatabase(context: Context) {
            if (INSTANCE == null) {
                synchronized(Appendable::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabese::class.java,
                        "online_savdo_db"
                    ).build()
                }
            }
        }

        fun getDatabase(): AppDatabese {
            return INSTANCE!!
        }
    }
}