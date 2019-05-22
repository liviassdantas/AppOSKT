package com.example.apposkotlin

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

//classe OS
//variáveis inicializadas
//c
@Entity
class OS (
    @PrimaryKey(autoGenerate = true)
    var num_os: Int = 0,
    var lista_serv: String? = null,
    var prod: String? = null,
    var cliente: String? = null,
    var endereco: String? = null,
    var numEndereco: String? = null,
    var bairro: String? = null,
    var cidade: String? = null,
    var estado: String? = null,
    var cep: String? = null)


