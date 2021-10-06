package com.example.samsarakmm.common.domain

import com.example.samsarakmm.common.domain.constants.HOUR_MODE
import java.io.Serializable

/**
 * Question: Why Serializable instead of Parcelable; isn't that less efficient?
 * Answer: Parcelable is designed for IPC (inter-process communication), not for persistent file
 * database. Since I am storing the object in a file instead for transferring it, and the docs
 * do not recommend Parcelable for persistent database, I use Serializable instead.
 *
 * https://developer.android.com/reference/android/os/Parcel
 * "This class (and the corresponding Parcelable API for placing arbitrary objects into a Parcel) is designed as a high-performance IPC transport. As such, it is not appropriate to place any Parcel data in to persistent database: changes in the underlying implementation of any of the data in the Parcel can render older data unreadable."
 */
class Day(//we provide a way of accessing the data which is secure
    //We hide the data itself for security and protection
    val mode: HOUR_MODE, val hours: Array<Hour>
) : Serializable