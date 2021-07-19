package model

import java.time.LocalDate
import java.time.LocalTime

data class Type2Record(
    override val user: String,
    override val machine: String,
    override val date: LocalDate,
    override val time: LocalTime,
    val program: String,
    val executionDurationInSeconds: Int,
    val filename: String,
    val fileActivity: FileActivity,
    val printerUsed: String,
    val pagesPrinted: Int
): InputRecord