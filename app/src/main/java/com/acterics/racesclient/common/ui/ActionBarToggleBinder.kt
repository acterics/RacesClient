package com.acterics.racesclient.common.ui

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.acterics.racesclient.R

/**
 * Created by root on 09.11.17.
 */
class ActionBarToggleBinder {
    lateinit var toolbar: Toolbar
    lateinit var activity: AppCompatActivity
    lateinit var drawerLayout: DrawerLayout

    var openContentRes: Int = R.drawable.ic_menu_black
    var closeContentRes: Int = R.drawable.ic_menu_black

    private var toggle: ActionBarDrawerToggle? = null

    fun bind() {
        toggle = getNewToggle().also {
            drawerLayout.addDrawerListener(it)
        }
    }

    fun unbind() {
        toggle?.let {
            drawerLayout.removeDrawerListener(it)
        }
    }

    private fun getNewToggle() =
        ActionBarDrawerToggle(activity, drawerLayout, toolbar, openContentRes, closeContentRes)

}