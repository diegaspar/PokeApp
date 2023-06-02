package com.diegaspar.core_base.data.persistence.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diegaspar.core_base.data.persistence.dao.PokemonDao
import com.diegaspar.core_base.data.persistence.model.PokemonEntity
import com.diegaspar.core_base.data.persistence.model.TypeListConverter

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(TypeListConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}