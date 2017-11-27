package com.acterics.racesclient.common.extentions

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import com.acterics.racesclient.common.constants.FileConsts
import java.io.File
import android.provider.MediaStore




/**
 * Created by root on 27.11.17.
 */
fun getEnvironmentPath(): String = Environment.getExternalStorageDirectory().path

fun createDirIfNotExists(): Boolean {
    val file = File(getEnvironmentPath() + FileConsts.APP_DIR)
    return file.exists() && file.mkdirs()
}

fun Context.getRealPathFromURI(contentUri: Uri): String? {
    var cursor: Cursor? = null
    var result: String? = null
    try {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        cursor = contentResolver.query(contentUri, projection, null, null, null)
        cursor?.let {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            result = it.getString(columnIndex)
        }
    } finally {
        cursor?.close()
    }
    return result
}