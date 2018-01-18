package com.acterics.racesclient.common.constants

import android.net.Uri
import android.os.Environment


/**
 * Created by root on 27.11.17.
 */
object FileConsts {
    const val APP_DIR = "/racesclient"
    const val AVATAR_NAME = "/avatar.jpg"
    val PIC_PATH = "${Environment.getExternalStorageDirectory().path}$APP_DIR$AVATAR_NAME"
    val PIC_URI = Uri.parse("file://$PIC_PATH")
}