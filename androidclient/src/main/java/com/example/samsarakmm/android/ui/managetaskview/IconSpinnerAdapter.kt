package com.example.samsarakmm.android.ui.managetaskview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

internal class IconSpinnerAdapter(context: Context?, items: List<IconSpinnerItem?>?) :
    ArrayAdapter<IconSpinnerItem?>(
        context!!, 0, items!!
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) convertView = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)
        val item = getItem(position)
        val itemText = convertView!!.findViewById<TextView>(android.R.id.text1)
        if (item != null) {
            itemText.text = item.itemText
        }
        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
//        if (convertView == null) convertView = LayoutInflater.from(context)
//            .inflate(R.layout.spinner_icon_dropdown_item, parent, false)
        val item = getItem(position)
//        val itemIcon = convertView!!.findViewById<ImageView>(R.id.imv_spinner_item_icon)
//        val itemText = convertView.findViewById<TextView>(R.id.lbl_spinner_item)
//        if (item != null) {
//            itemIcon.setImageResource(item.itemIconResId)
//            itemText.text = item.itemText
//        }
        return convertView!!
    }
}