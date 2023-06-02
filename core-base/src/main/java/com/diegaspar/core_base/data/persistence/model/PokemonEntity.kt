package com.diegaspar.core_base.data.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class PokemonEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "typeList") val typeList: List<String>?
)


object TypeListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        return Gson().toJson(list)
    }
}
