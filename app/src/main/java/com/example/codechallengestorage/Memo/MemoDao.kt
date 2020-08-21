package com.example.codechallengestorage.Memo

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface MemoDao{
    @Insert (onConflict = REPLACE)
    fun addMemo (memo : Memo) : Long

    @Query ("SELECT*FROM Memo")
    fun getMemo() : List<Memo>

    @Update
    fun updateMemo (memo: Memo): Int

    @Delete
    fun deleteMemo (memo: Memo):Int
}