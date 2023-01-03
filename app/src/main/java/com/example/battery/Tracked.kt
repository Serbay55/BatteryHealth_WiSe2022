package com.example.battery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_charges")
data class Tracked(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "charge_up") val chargeUp: Int?,
    @ColumnInfo(name = "charge_down") val chargeDown: Int?,
    @ColumnInfo(name = "date_of_occurrence") val date: Long,


)


