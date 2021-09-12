package com.example.rhmanager.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Execution(
    val id: String,
    @SerialName("ipo_access_execution_rank")
    val ipoAccessExecutionRank: String?,
    val price: String,
    val quantity: String,
    @SerialName("settlement_date")
    val settlementDate: String,
    val timestamp: String
)