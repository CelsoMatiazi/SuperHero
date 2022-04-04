package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.PersonagemEntity

@Dao
interface PersonagemDao {
    @Query("select * from personagem")
    fun getAll(): List<PersonagemEntity>

    @Query("""
        select * from personagem where
            personagem.id = :id or
            personagem.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): PersonagemEntity

    @Insert
    fun create(body: PersonagemEntity)

    @Query("""
        delete from personagem where
            personagem.id = :id or
            personagem.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?): Unit
}