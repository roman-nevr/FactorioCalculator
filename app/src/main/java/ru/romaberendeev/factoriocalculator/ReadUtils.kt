package ru.romaberendeev.factoriocalculator

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.reflect.Type

object ReadUtils {

  fun readAssetFile(
    context: Context, @RawRes resId: Int
  ): String {
    return try {
      val openRawResource: InputStream = context.resources.openRawResource(resId)
      return readAll(openRawResource)
    } catch (e: Exception) {
      throw e
    }
  }

  fun readFile(classLoader: ClassLoader, file: String): String {
    val inputStream = classLoader.getResourceAsStream(file)
    return readAll(inputStream)
  }

  private fun readAll(inputStream: InputStream): String {
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    var line = bufferedReader.readLine()
    val builder = StringBuilder()
    while (!line.isNullOrBlank()) {
      builder.append(line)
      line = bufferedReader.readLine()
    }
    bufferedReader.close()
    return builder.toString()
  }

  inline fun <reified T: Any> Class<T>.listToken(): Type {
    return object : TypeToken<List<T>>() {}.type
  }
}