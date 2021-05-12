package com.example.onlinesavdo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id: Int,
    val title: String,
    val icon: String,
    var checked: Boolean = false
)