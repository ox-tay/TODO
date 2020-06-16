package com.bilgiland.todo.ui.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())

        setViewModel()

        restInti()
    }

    /**
     * init the rest of operation
     */
    abstract fun restInti()

    /**
     * set viewModel
     */
    abstract fun setViewModel()

    /**
     * get Layout to show
     */
    abstract fun getLayout(): Int


}