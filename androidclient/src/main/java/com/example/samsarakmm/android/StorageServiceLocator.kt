package com.example.samsarakmm.android

import android.content.Context
import com.example.samsarakmm.android.persistence.FakeLocalDayStorageImpl
import com.example.samsarakmm.android.persistence.FakeLocalTaskStorageImpl
import com.example.samsarakmm.domain.IDayStorage
import com.example.samsarakmm.domain.ITaskStorage


class StorageServiceLocator(context: Context) {
    val dayStorageImpl: IDayStorage
    val taskStorageImpl: ITaskStorage

    /**
     * Why toString? By passing the String instead of context, I can keep the Android code out of
     * my backend components. This class inevitably must interact with Context, so I can work with
     * it here.
     * @param context
     */
    init {
        val exec = ApplicationExecutors()
        dayStorageImpl = FakeLocalDayStorageImpl()
        taskStorageImpl = FakeLocalTaskStorageImpl(
        )
    }
}