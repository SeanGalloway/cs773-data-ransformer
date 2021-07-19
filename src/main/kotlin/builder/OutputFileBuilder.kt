package builder

import model.OutputRecord
import java.io.OutputStream

class OutputFileBuilder {
    fun parseToOutputFile(outputRecords: List<OutputRecord>): String {
        return fileHeader + outputRecords.joinToString(separator = "\n")
    }

    companion object {
        val fileHeader = "userId,mostCommonMachine,usedOtherMachines,avgSessionLengthInSeconds,avgActivityTime," +
                "mostCommonEmailProgram,mostCommonEmailCorrespondent,mostCommonEmailAction,hasActivityOnSndy," +
                "hasActivityOnMndy,hasActivityOnTzdy,hasActivityOnWdsy,hasActivityOnThdy,hasActivityOnFrdy," +
                "hasActivityOnStdy,avgCharactersTyped,mostCharactersTyped,leastCharactersTyped,lowestFileRead," +
                "highestFileRead,numberOfFileReads,lowestFileWrite,highestFileWrite,numberOfFileWrites," +
                "mostUsedPrinter,mostPagesPrinted,avgPagesPrinted\n"
    }
}