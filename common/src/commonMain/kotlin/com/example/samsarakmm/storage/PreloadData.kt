package com.example.samsarakmm.storage

import com.example.samsarakmm.domain.*
import com.example.samsarakmm.domain.constants.COLOR
import com.example.samsarakmm.domain.constants.HOUR_MODE
import com.example.samsarakmm.domain.constants.ICON
import com.example.samsarakmm.domain.constants.QUARTER

/**
 * Question: What the hell is this class and why did I choose to hard code this data in Java?
 * The purpose of this class is to pre-load my storage mechanism (which is for now just using Java/
 * Android File storage) with the data that I want to preload.
 *
 * I could have placed this data in the assets folder as a JSON or XML file, or download it from
 * a remote server (in later iterations I will probably do that as it would allow for language
 * localization), but for the time being, adding the extra step when it ends up being deserialized
 * to Java objects anyways is basically just a waste of time for no net benefit to memory space as
 * far as I'm aware.
 *
 * Understand that THIS IS THE BETA VERSION.
 *
 * Class is left without access modifier to denote "package private"
 */
internal object PreloadData {
    //TODO apply Lazy delegates so we don't unnecessarily load this data into memory
    /**
     * Returns the initial set of tasks which the user can work with. I allow the user to update
     * the names of these tasks as they see fit, and to assign various ICONs to the tasks from a
     * finite set of Icons, but the user is not allowed to add or remove tasks in this version of
     * the App.
     * @return
     */
    val preloadedTasks: Tasks
        get() = Tasks(
            arrayOf(
                Task(0, "Free Time", ICON.FREE_TIME, COLOR.GREEN),
                Task(1, "Break", ICON.BREAK, COLOR.DARK_BLUE),
                Task(2, "Study", ICON.STUDY, COLOR.TEAL),
                Task(3, "Work", ICON.WORK, COLOR.DARK_RED),
                Task(4, "Exercise", ICON.EXERCISE, COLOR.BURNT_ORANGE),
                Task(5, "Meditate", ICON.MENTAL_CULTIVATION, COLOR.LIGHT_BLUE),
                Task(6, "Eat", ICON.EAT, COLOR.BROWN),
                Task(7, "Sleep", ICON.SLEEP, COLOR.MAUVE),
                Task(8, "Shop", ICON.SHOP, COLOR.DARK_LIME)
            )
        )

    //sleep 22-6 hours (10pm-6am)
    //Exercise half hour
    //Mental Cultivation half hour
    //work from 7-11
    //eat half hour
    //break half hour
    //work four hours
    // 4pm Eat half hour
    // Shop half hour
    // 5pm Free time four hours
    //9pm half hour study
    //half hour meditate
    //10pm sleep
    val preloadedDay: Day
        get() = Day(
            HOUR_MODE.TWELVE_HOUR, arrayOf( //sleep 22-6 hours (10pm-6am)
                getHour(7, 0),
                getHour(7, 1),
                getHour(7, 2),
                getHour(7, 3),
                getHour(7, 4),
                getHour(7, 5),  //Exercise half hour
                //Mental Cultivation half hour
                Hour(
                    arrayOf(
                        QuarterHour(1, QUARTER.ZERO, true),
                        QuarterHour(2, QUARTER.FIFTEEN, true),
                        QuarterHour(3, QUARTER.THIRTY, true),
                        QuarterHour(4, QUARTER.FOURTY_FIVE, true)
                    ),
                    6
                ),  //work from 7-11
                getHour(3, 7),
                getHour(3, 8),
                getHour(3, 9),
                getHour(3, 10),  //eat half hour
                //break half hour
                Hour(
                    arrayOf(
                        QuarterHour(6, QUARTER.ZERO, true),
                        QuarterHour(6, QUARTER.FIFTEEN, false),
                        QuarterHour(6, QUARTER.THIRTY, false),
                        QuarterHour(1, QUARTER.FOURTY_FIVE, true)
                    ),
                    11
                ),  //work four hours
                getHour(3, 12),
                getHour(3, 13),
                getHour(3, 14),
                getHour(3, 15),  // 4pm Eat half hour
                // Shop half hour
                Hour(
                    arrayOf(
                        QuarterHour(6, QUARTER.ZERO, true),
                        QuarterHour(6, QUARTER.FIFTEEN, false),
                        QuarterHour(8, QUARTER.THIRTY, true),
                        QuarterHour(8, QUARTER.FOURTY_FIVE, false)
                    ),
                    16
                ),  // 5pm Free time four hours
                getHour(0, 17),
                getHour(0, 18),
                getHour(0, 19),
                getHour(0, 20),  //9pm half hour study
                //half hour meditate
                Hour(
                    arrayOf(
                        QuarterHour(2, QUARTER.ZERO, true),
                        QuarterHour(2, QUARTER.FIFTEEN, false),
                        QuarterHour(5, QUARTER.THIRTY, true),
                        QuarterHour(5, QUARTER.FOURTY_FIVE, false)
                    ),
                    21
                ),  //10pm sleep
                getHour(7, 22),
                getHour(7, 23)
            )
        )

    fun getHour(taskId: Int, hourInt: Int): Hour {
        return Hour(
            arrayOf(
                QuarterHour(taskId, QUARTER.ZERO, true),
                QuarterHour(taskId, QUARTER.FIFTEEN, false),
                QuarterHour(taskId, QUARTER.THIRTY, false),
                QuarterHour(taskId, QUARTER.FOURTY_FIVE, false)
            ),
            hourInt
        )
    }
}