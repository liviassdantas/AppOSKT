package com.example.apposkotlin

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


//classe OS
//variáveis inicializadas

//nome da tabela
@Entity(tableName = "OS")
data class OS (
    //chave primária(declarar antes da coluna)
    @PrimaryKey var num_os: Int = 0,
    //Coluna e os campos
    @ColumnInfo(name = "lista_serv")var lista_serv: String? = null,
    @ColumnInfo(name = "prod")var prod: String? = null,
    @ColumnInfo(name = "cliente")var cliente: String? = null,
    @ColumnInfo(name = "endereco")var endereco: String? = null,
    @ColumnInfo(name = "numEndereco")var numEndereco: String? = null,
    @ColumnInfo(name = "bairro")var bairro: String? = null,
    @ColumnInfo(name = "cidade")var cidade: String? = null,
    @ColumnInfo(name = "estado")var estado: String? = null,
    @ColumnInfo(name = "cep")var cep: String? = null)


