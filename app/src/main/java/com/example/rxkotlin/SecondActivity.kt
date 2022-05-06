package com.example.rxkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxkotlin.databinding.ActivitySecondBinding
import com.example.rxkotlin.db.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this )
        initViews()
    }

    @SuppressLint("CheckResult")
    private fun initViews() {
        val id = intent.getIntExtra("id", 0)

        appDatabase.userDao().getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.tvUser.text = it.username + " " + it.password
            }){

            }

    }
}