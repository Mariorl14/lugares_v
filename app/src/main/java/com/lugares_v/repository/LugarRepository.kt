package com.lugares_v.repository

import androidx.lifecycle.LiveData
import com.lugares_v.data.LugarDao
import com.lugares_v.model.Lugar
class LugarRepository(private val lugarDao: LugarDao) {
    suspend fun saveLugar(lugar: Lugar) {
        if (lugar.id == 0){
            lugarDao.addLugar(lugar)
        }else {
            lugarDao.updateLugar(lugar)
        }
        lugarDao.addLugar(lugar)
    }
    suspend fun updateLugar(lugar: Lugar) {
        lugarDao.updateLugar(lugar)
    }
    suspend fun deleteLugar(lugar: Lugar) {
        lugarDao.deleteLugar(lugar)
    }
    val getLugares : LiveData<List<Lugar>> = lugarDao.getLugares()
}