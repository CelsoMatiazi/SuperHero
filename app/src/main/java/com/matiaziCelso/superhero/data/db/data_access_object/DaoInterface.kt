package com.matiaziCelso.superhero.data.db.data_access_object

interface DaoInterface<Entity> {
    fun getAll(): List<Entity>
    fun getOne(id: Int?, external_id: String?): Entity
    fun create(body: Entity): Entity
    fun delete(id: Int?, external_id: String?): Unit
}
