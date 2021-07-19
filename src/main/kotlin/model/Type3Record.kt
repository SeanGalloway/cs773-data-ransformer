package model

import java.time.LocalDate
import java.time.LocalTime

data class Type3Record(
    override val user: String,
    override val machine: String,
    override val date: LocalDate,
    override val time: LocalTime,
    val program: String,
    val address: String,
    val activity: EmailActivity,
    val sizeInBytes: Int,
    val numberOfAttachments: Int
): InputRecord