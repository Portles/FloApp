package com.h5190001.flo.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun BuildToast(context: Context, message: String, messageType: MESSAGE_TYPE) {
        if (messageType == MESSAGE_TYPE.LONG_MESSAGE) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } else if (messageType == MESSAGE_TYPE.SHORT_MESSAGE) {
            Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
        }
    }
}

enum class MESSAGE_TYPE() {
    SHORT_MESSAGE,
    LONG_MESSAGE
}