package com.example.samsarakmm.domain

import java.io.Serializable

/**
 * Represents an hour of a given day. An hour is divided into four quarters (15 minute intervals)
 */
class Hour(
    /**
     * NOTE: the first QuarterHour in quarters may never be inactive!
     * This is enforced in the UI but must be respected elsewhere
     */
    val quarters: Array<QuarterHour>,
    /**
     * One of:
     * Integers 0-23 where 0 is 12:00AM or 0:00, 23 is 11:00pm or 23:00hrs
     */
    val hourInteger: Int
) : Serializable