package com.example.codechallengestorage.Memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class DbMemo : RoomDatabase() {
    abstract fun memoDao() : MemoDao

    companion object{
        private var INSTANCE: DbMemo? = null

        fun getInstance (context: Context) : DbMemo?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    DbMemo::class.java,
                    "db_memo"
                ).build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}