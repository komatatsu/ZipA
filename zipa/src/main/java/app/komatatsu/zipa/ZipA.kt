package app.komatatsu.zipa

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.nio.charset.Charset

object ZipA {

    private const val FILE_PREFECTURE = "KEN_ALL.CSV"
    private const val FILE_OFFICE = "JIGYOSYO.CSV"
    private val sjis = Charset.forName("SJIS")

    @JvmStatic
    fun search(context: Context, zip: String?): String? {
        if (zip == null) return null

        initialize(context)
        return getSharedPreference(context).getString(wash(zip), null)
    }

    @JvmStatic
    @SuppressLint("ApplySharedPref")
    fun initialize(context: Context) {
        val sharedPreferences = getSharedPreference(context)
        if (!sharedPreferences.contains("version")) {
            val prefecture = loadPrefectureData(context)
            val office = loadOfficeData(context)
            if (prefecture && office) {
                sharedPreferences
                    .edit()
                    .putString("version", BuildConfig.VERSION_NAME)
                    .commit()
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun loadPrefectureData(context: Context): Boolean {
        return try {
            val editor = getSharedPreference(context).edit()
            context.assets.open(FILE_PREFECTURE).bufferedReader(sjis).useLines {
                for (row in it.iterator()) {
                    val data = row.split(",")
                    editor.putString(wash(data[2]), wash(data[6] + data[7] + data[8]))
                }
            }
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun loadOfficeData(context: Context): Boolean {
        return try {
            val editor = getSharedPreference(context).edit()
            context.assets.open(FILE_OFFICE).bufferedReader(sjis).useLines {
                for (row in it.iterator()) {
                    val data = row.split(",")
                    editor.putString(wash(data[7]), wash(data[3] + data[4] + data[5] + data[6]))
                }
            }
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun wash(value: String?): String? {
        if (value == null) return null
        return value.trim().replace("\"", "").replace("-", "")
    }

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
    }
}
