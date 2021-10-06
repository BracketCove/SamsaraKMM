
import com.example.samsarakmm.common.domain.constants.HOUR_MODE
import com.example.samsarakmm.common.domain.constants.QUARTER


fun getHourBlockText(hour: Int, mode: HOUR_MODE): String {
    var suffix = ""
    var hourText = hour.toString()

    //Twelve hour clock is stupid
    if (mode == HOUR_MODE.TWELVE_HOUR) {
        if (hour == 0) {
            suffix = "AM"
            hourText = "12"
        }
        if (hour == 12) suffix = "PM"
        else if (hour > 12) {
            //hour is 13-23 (1PM - 11PM)
            suffix = "PM"
            hourText = "${hour - 12}"
        } else {
            //hour is 01-11 (1PM - 11PM)
            suffix = "AM"
        }
    } else if (mode == HOUR_MODE.TWENTY_FOUR_HOUR) {
        //else if included for legibility, in case you were wondering
        if (hour == 0) {
            //military clock uses 00, not 0 for 12:00AM equivalent
            hourText = "00"
        }
        //either way suffix is the same
        suffix = ":00"
    }
    return hourText + suffix
}

fun getHourToggleViewFormattedText(q: QUARTER, hour: Int, mode: HOUR_MODE): String {
    var suffix = ""
    val quarterText = getQuarterText(q)


    var hourText = "$hour"

    //Twelve hour clock is stupid
    if (mode == HOUR_MODE.TWELVE_HOUR) {
        if (hour == 0) {
            suffix = "AM"
            hourText = "12"
        } else if (hour == 12) {
            suffix = "PM"
        }
        else if (hour > 12) {
            //hour is 13-23 (1PM - 11PM)
            suffix = "PM"
            hourText = "${hour - 12}"
        } else {
            //hour is 01-11 (1PM - 11PM)
            suffix = "AM"
        }
    } else if (mode == HOUR_MODE.TWENTY_FOUR_HOUR) {
        //else if included for legibility, in case you were wondering
        if (hour == 0) {
            //military clock uses 00, not 0 for 12:00AM equivalent
            hourText = "00"
        }
    }
    return hourText + quarterText + suffix
}

private fun getQuarterText(q: QUARTER): String {
    return when (q) {
        QUARTER.FIFTEEN -> ":15"
        QUARTER.THIRTY -> ":30"
        QUARTER.FOURTY_FIVE -> ":45"
        else -> ":00"
    }
}