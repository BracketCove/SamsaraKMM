package com.example.samsarakmm.common.domain

import com.example.samsarakmm.common.domain.constants.QUARTER
import java.io.Serializable

/**
 * A virtual representation of a quarter of an hour; i.e. a 15  minute interval.
 *
 *
 *
 */
class QuarterHour(val taskId: Int, val quarter: QUARTER, val isActive: Boolean) : Serializable