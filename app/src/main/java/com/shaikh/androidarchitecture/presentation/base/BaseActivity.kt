package com.shaikh.androidarchitecture.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator
import com.shaikh.androidarchitecture.presentation.utilities.showToast

abstract class BaseActivity<M : ViewModel> : AppCompatActivity() {

    lateinit var viewModel: M

    private var toastInstance: Toast? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        generateViewModel()
        setObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

    fun showMessage(message: String) {
        toastInstance = showToast(message)
    }

    override fun onDestroy() {
        toastInstance?.cancel() // cancel and toast message that is being displayed
        super.onDestroy()
    }

}
