package fastcampus.aop.aop_part5_chapter06_repeat.data.preference

interface PreferenceManager {

    fun getLong(key: String): Long?

    fun putLong(key: String, value: Long)
}