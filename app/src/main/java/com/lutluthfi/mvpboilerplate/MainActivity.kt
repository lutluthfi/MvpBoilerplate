package com.lutluthfi.mvpboilerplate

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.lutluthfi.mvpboilerplate.base.BaseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override fun initContentView(): Int = R.layout.activity_main
//
//    override fun initComponentView() {
//        textView = findViewById(R.id.text)
//    }
//
//    override fun initDependency() {
//    }
//
//    override fun setupView() {
//        val text = "Click me!"
//        textView.text = text
//    }
//
//    override fun setupListener() {
//        textView.setOnClickListener { toastMessage("$TAG: It's me!") }
//    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
