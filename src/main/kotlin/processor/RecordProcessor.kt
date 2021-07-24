package processor

import model.*
import java.time.DayOfWeek
import java.time.LocalTime
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class RecordProcessor {

    fun buildUserRecords(inputRecords: List<InputRecord>): List<OutputRecord> {
        return inputRecords.groupBy { it.user }.map {recordsForOneUser ->
            buildIndividualUserRecord(recordsForOneUser.value)
        }
    }

    private fun buildIndividualUserRecord(userRecords: List<InputRecord>): OutputRecord {

        val type1Records = userRecords.filterIsInstance<Type1Record>()
        val type2Records = userRecords.filterIsInstance<Type2Record>()
        val type3Records = userRecords.filterIsInstance<Type3Record>()

        val allActivityRecords = type2Records.plus(type3Records)

        return OutputRecord(
            userId = userRecords.first().user,
            mostCommonMachine = type1Records.selectMostCommon { it.machine } ?: "NA",
            usedOtherMachines = type1Records.groupBy { it.machine }.size > 1,
            avgSessionLengthInSeconds = type1Records.map { it.logoutTime.toSecondOfDay() - it.time.toSecondOfDay()}.average().toBigDecimal(),
            avgActivityTime = LocalTime.MIDNIGHT.plusSeconds(allActivityRecords.map { it.time.toSecondOfDay() }.average().roundToLong()),
            mostCommonEmailProgram = type3Records.selectMostCommon { it.program } ?: "NA",
            mostCommonEmailCorrespondent = type3Records.selectMostCommon { it.address } ?: "NA",
            mostCommonEmailAction = type3Records.selectMostCommon { it.activity },
            hasActivityOnSndy = userRecords.any { it.date.dayOfWeek == DayOfWeek.SUNDAY },
            hasActivityOnMndy = userRecords.any { it.date.dayOfWeek == DayOfWeek.MONDAY },
            hasActivityOnTzdy = userRecords.any { it.date.dayOfWeek == DayOfWeek.TUESDAY },
            hasActivityOnWdsy = userRecords.any { it.date.dayOfWeek == DayOfWeek.WEDNESDAY },
            hasActivityOnThdy = userRecords.any { it.date.dayOfWeek == DayOfWeek.THURSDAY },
            hasActivityOnFrdy = userRecords.any { it.date.dayOfWeek == DayOfWeek.FRIDAY },
            hasActivityOnStdy = userRecords.any { it.date.dayOfWeek == DayOfWeek.SATURDAY },
            avgCharactersTyped = type1Records.map { it.charactersTyped }.average().toBigDecimal(),
            mostCharactersTyped = type1Records.maxOf { it.charactersTyped },
            leastCharactersTyped = type1Records.minOf { it.charactersTyped },
            lowestFileRead = type2Records.filter { it.fileActivity == FileActivity.R }.minOfOrNull { it.filename } ?: "NA",
            highestFileRead = type2Records.filter { it.fileActivity == FileActivity.R }.maxOfOrNull { it.filename } ?: "NA",
            numberOfFileReads = type2Records.filter { it.fileActivity == FileActivity.R }.size,
            lowestFileWrite = type2Records.filter { it.fileActivity == FileActivity.RW }.minOfOrNull { it.filename } ?: "NA",
            highestFileWrite = type2Records.filter { it.fileActivity == FileActivity.RW }.maxOfOrNull { it.filename } ?: "NA",
            numberOfFileWrites = type2Records.filter { it.fileActivity == FileActivity.RW }.size,
            mostUsedPrinter = type2Records.selectMostCommon { it.printerUsed } ?: "NA",
            mostPagesPrinted = type2Records.maxOf { it.pagesPrinted },
            avgPagesPrinted = type2Records.map { it.pagesPrinted }.average().toBigDecimal(),
        )
    }

    private inline fun <T, K> Iterable<T>.selectMostCommon(keySelector: (T) -> K): K? {
        return this.groupBy { keySelector(it) }.maxByOrNull { it.value.size }?.value?.first()?.let {
            keySelector(it)
        }
    }
}