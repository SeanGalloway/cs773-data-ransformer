import builder.OutputFileBuilder
import fileReader.InputCsvReader
import processor.RecordProcessor
import java.io.File
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    val reader = InputCsvReader()
    val processor = RecordProcessor()
    val builder = OutputFileBuilder()

    if (args.size != 2)
        throw IllegalStateException("Must provide filenames for input and output")

    val file: List<String> = File(args.first()).readLines()

    val records = reader.readInputFile(file.subList(1,file.size-1))

    val outputRecords = processor.buildUserRecords(records)

    File(args[1]).writeBytes(builder.parseToOutputFile(outputRecords).toByteArray())
}