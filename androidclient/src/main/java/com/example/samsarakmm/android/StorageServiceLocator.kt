package com.example.samsarakmm.android

import android.content.Context
import com.example.samsarakmm.storage.DatabaseDriverFactory
import com.example.samsarakmm.storage.StorageService
import com.example.samsarakmm.storage.IStorageService


class StorageServiceLocator(context: Context) {
    val storage: IStorageService = StorageService(
        DatabaseDriverFactory(
            context
        )
    )
}