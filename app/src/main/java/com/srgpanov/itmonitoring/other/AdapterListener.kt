package com.srgpanov.itmonitoring.other

import android.view.View

interface AdapterListener {
    fun onClick(view: View, position: Int)
}