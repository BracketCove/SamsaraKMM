package com.example.samsarakmm.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.constants.COLOR
import com.example.samsarakmm.common.ui.toImageVector
import com.example.samsarakmm.common.ui.typography


//TODO define the buttons in the parent layouts due to eventhandler's return type
@Composable
fun TaskPickerButton(
    task: Task,
    modifier: Modifier
) {
    Column(
        modifier
    ) {
        Text(
            text = task.taskName,
            style = typography.h4
        )

        Image(
            imageVector = task.taskIcon.toImageVector,
            task.taskName
        )
    }

}

