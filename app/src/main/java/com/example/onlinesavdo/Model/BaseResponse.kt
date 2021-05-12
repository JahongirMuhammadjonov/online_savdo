package com.example.onlinesavdo.Model

data class BaseResponse<T>(
    val success:Boolean,
    val data:T,
    val message:String,
    val error_code:Int
)