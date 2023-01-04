package com.example.battery

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import com.example.battery.databinding.ActivityMainBinding
import com.example.battery.ui.main.SectionsPagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoField

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB : AppDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = findViewById<Button>(R.id.button)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        button.setOnClickListener { writeData() }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun writeData(){
        val tracked = Tracked(null, chargeUp = 1, chargeDown = 1, date = LocalDateTime.now().getLong(
            ChronoField.DAY_OF_YEAR))
        GlobalScope.launch(Dispatchers.IO){appDB.trackedBatDao().insert(tracked)}
        Toast.makeText(null, "Successfull", Toast.LENGTH_SHORT ).show()
    }
    private fun readData(){
        lateinit var tracked : Tracked

        GlobalScope.launch { tracked = appDB.trackedBatDao().findByChargeUp(1)
        Toast.makeText(null, ""+tracked.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}