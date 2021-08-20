package com.example.samsarakmm.android.ui.ui.dayview


/**
 * Type for the whole item
 */
enum class LIST_ITEM_TYPE {
    FULL_HOUR,
    HALF_HALF,
    QUARTER_QUARTER_HALF,
    QUARTER_HALF_QUARTER,
    HALF_QUARTER_QUARTER,
    QUARTER_THREE_QUARTER,
    THREE_QUARTER_QUARTER,
    QUARTER_QUARTER_QUARTER_QUARTER
}

/**
 * Type for the 1-4 blocks which make up each item
 */
enum class LIST_ITEM_BLOCK_TYPE {
    BLOCK_HOUR,
    BLOCK_THREE_QUARTER,
    BLOCK_HALF,
    BLOCK_QUARTER
}