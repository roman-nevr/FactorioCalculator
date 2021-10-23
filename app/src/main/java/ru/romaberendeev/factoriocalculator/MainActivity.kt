package ru.romaberendeev.factoriocalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val readAssetFile = ReadUtils.readAssetFile(this, R.raw.recepies)
    println(readAssetFile)
  }
}