package fileReader

import model.*
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class InputCsvReader {

    fun readInputFile(input: List<String>): List<InputRecord> {
        return input.map {
            readInputRecord(it)
        }
    }

    private fun readInputRecord(record: String): InputRecord {
        val splitRecord = record.split(",")
        return when(splitRecord.first()) {
            "1" -> buildType1Record(splitRecord)
            "2" -> buildType2Record(splitRecord)
            "3" -> buildType3Record(splitRecord)
            else -> throw IllegalArgumentException("Invalid record type: "+ splitRecord.first())
        }
    }

    private fun buildType1Record(splitRecord: List<String>): Type1Record {
        if (splitRecord.size != 11)
            throw IllegalArgumentException("Type 1 record must have 11 attributes:\n"+ splitRecord.joinToString(separator = ","))

        return Type1Record(
            user = splitRecord[1],
            machine = splitRecord[2],
            date = LocalDate.parse(splitRecord[3], DateTimeFormatter.ofPattern("MMddyy")),
            time = LocalTime.parse(splitRecord[4], DateTimeFormatter.ofPattern("HHmmss")),
            logoutTime = LocalTime.parse(splitRecord[5], DateTimeFormatter.ofPattern("HHmmss")),
            numberOfProcesses = splitRecord[6].toInt(),
            maxProcesses = splitRecord[7].toInt(),
            charactersTyped = splitRecord[8].toInt(),
            cpuUse = splitRecord[9].toInt()
        )
    }

    private fun buildType2Record(splitRecord: List<String>): Type2Record {
        if (splitRecord.size != 11)
            throw IllegalArgumentException("Type 2 record must have 11 attributes:\n"+ splitRecord.joinToString(separator = ","))

        return Type2Record(
            user = splitRecord[1],
            machine = splitRecord[2],
            date = LocalDate.parse(splitRecord[3], DateTimeFormatter.ofPattern("MMddyy")),
            time = LocalTime.parse(splitRecord[4], DateTimeFormatter.ofPattern("HHmmss")),
            program = splitRecord[5],
            executionDurationInSeconds = splitRecord[6].toInt(),
            filename = splitRecord[7],
            fileActivity = FileActivity.valueOf(splitRecord[8]),
            printerUsed = splitRecord[9],
            pagesPrinted = splitRecord[10].toInt()
        )
    }

    private fun buildType3Record(splitRecord: List<String>): Type3Record {
        if (splitRecord.size != 11)
            throw IllegalArgumentException("Type 3 record must have 11 attributes:\n"+ splitRecord.joinToString(separator = ","))

        return Type3Record(
            user = splitRecord[1],
            machine = splitRecord[2],
            date = LocalDate.parse(splitRecord[3], DateTimeFormatter.ofPattern("MMddyy")),
            time = LocalTime.parse(splitRecord[4], DateTimeFormatter.ofPattern("HHmmss")),
            program = splitRecord[5],
            address = splitRecord[6],
            activity = EmailActivity.valueOf(splitRecord[7]),
            sizeInBytes = splitRecord[8].toInt(),
            numberOfAttachments = splitRecord[9].toInt()
        )
    }
}