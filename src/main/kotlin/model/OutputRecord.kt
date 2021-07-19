package model

import java.math.BigDecimal
import java.time.LocalTime

data class OutputRecord (
    val userId: String,
    val mostCommonMachine: String,
    val usedOtherMachines: Boolean,
    val avgSessionLengthInSeconds: BigDecimal,
    val avgActivityTime: LocalTime,
    val mostCommonEmailProgram: String,
    val mostCommonEmailCorrespondent: String,
    val mostCommonEmailAction: EmailActivity?,
    val hasActivityOnSndy: Boolean,
    val hasActivityOnMndy: Boolean,
    val hasActivityOnTzdy: Boolean,
    val hasActivityOnWdsy: Boolean,
    val hasActivityOnThdy: Boolean,
    val hasActivityOnFrdy: Boolean,
    val hasActivityOnStdy: Boolean,
    val avgCharactersTyped: BigDecimal,
    val mostCharactersTyped: Int,
    val leastCharactersTyped: Int,
    val lowestFileRead: String,
    val highestFileRead: String,
    val numberOfFileReads: Int,
    val lowestFileWrite: String,
    val highestFileWrite: String,
    val numberOfFileWrites: Int,
    val mostUsedPrinter: String,
    val mostPagesPrinted: Int,
    val avgPagesPrinted: BigDecimal
) {
    override fun toString(): String {
        return userId + "," +
        mostCommonMachine + "," +
        usedOtherMachines.toFileFlag() + "," +
        avgSessionLengthInSeconds + "," +
        avgActivityTime.toSecondOfDay() + "," +
        mostCommonEmailProgram + "," +
        mostCommonEmailCorrespondent + "," +
        mostCommonEmailAction + "," +
        hasActivityOnSndy.toFileFlag() + "," +
        hasActivityOnMndy.toFileFlag() + "," +
        hasActivityOnTzdy.toFileFlag() + "," +
        hasActivityOnWdsy.toFileFlag() + "," +
        hasActivityOnThdy.toFileFlag() + "," +
        hasActivityOnFrdy.toFileFlag() + "," +
        hasActivityOnStdy.toFileFlag() + "," +
        avgCharactersTyped + "," +
        mostCharactersTyped + "," +
        leastCharactersTyped + "," +
        lowestFileRead + "," +
        highestFileRead + "," +
        numberOfFileReads + "," +
        lowestFileWrite + "," +
        highestFileWrite + "," +
        numberOfFileWrites + "," +
        mostUsedPrinter + "," +
        mostPagesPrinted + "," +
        avgPagesPrinted
    }

    private fun Boolean.toFileFlag(): String = if (this) "1" else "0"
}