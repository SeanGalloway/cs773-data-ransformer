package model

import java.time.LocalDate
import java.time.LocalTime

data class Type1Record(
    override val user: String,
    override val machine: String,
    override val date: LocalDate,
    override val time: LocalTime,
    val logoutTime: LocalTime,
    val numberOfProcesses: Int,
    val maxProcesses: Int,
    val charactersTyped: Int,
    val cpuUse: Int
): InputRecord