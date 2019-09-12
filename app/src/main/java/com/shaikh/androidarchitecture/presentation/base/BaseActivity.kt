package com.shaikh.androidarchitecture.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<M : ViewModel> : AppCompatActivity() {

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setObservers()

    abstract fun getViewModelClass(): Class<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        createViewModel()
        setObservers()
    }

    private fun createViewModel() {
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}