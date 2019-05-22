package com.example.apposkotlin

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


//classe OS
//variáveis inicializadas

//nome da tabela
@Entity(tableName = "OS")
data class OS (
    //chave primária(declarar antes da coluna)
    @PrimaryKey var num_os: Int = 0,
    //Coluna e os campos
    @ColumnInfo(name = "os_banco")
    var lista_serv: String? = null,
    var prod: String? = null,
    var cliente: String? = null,
    var endereco: String? = null,
    var numEndereco: String? = null,
    var bairro: String? = null,
    var cidade: String? = null,
    var estado: String? = null,
    var cep: String? = null)


