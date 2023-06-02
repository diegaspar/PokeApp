package com.diegaspar.core_base.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.diegaspar.core_base.data.persistence.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity WHERE id = :id ")
    fun findById(id: String): PokemonEntity

    @Insert(onConflict = REPLACE)
    fun insert(pokemon: PokemonEntity)
}