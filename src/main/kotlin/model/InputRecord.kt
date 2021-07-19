package model

import java.time.LocalDate
import java.time.LocalTime

interface InputRecord {
    val user: String
    val machine: String
    val date: LocalDate
    val time: LocalTime
}