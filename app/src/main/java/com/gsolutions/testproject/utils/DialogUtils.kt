package com.gsolutions.testproject.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.gsolutions.testproject.R



fun showLogOutDialog(context: Context,
                             action: () -> Unit) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.alert_storage_permission)
    dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val yesBtn = dialog.findViewById(R.id.confirm_btn) as TextView
    val noBtn = dialog.findViewById(R.id.cancel_btn) as TextView
    yesBtn.setOnClickListener {
        action.invoke()
        dialog.dismiss()
    }
    noBtn.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}

