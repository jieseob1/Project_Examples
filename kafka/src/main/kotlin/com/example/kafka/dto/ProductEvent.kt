package com.example.kafka.dto

data class ProductEvent(
    val platform: String,  // "naver" or "coupang"
    val salesStoreId: String,
    val productSellId: String,
    val status: String,    // "fail" or "request"
    val price: String,
    val deliveryFee: String
)
