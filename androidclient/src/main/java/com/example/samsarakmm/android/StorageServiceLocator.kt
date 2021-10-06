package com.example.samsarakmm.android

import android.content.Context
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService


class StorageServiceLocator(context: Context) {
    val storage: IStorageService = StorageService(
        DatabaseDriverFactory(
            context
        )
    )
}