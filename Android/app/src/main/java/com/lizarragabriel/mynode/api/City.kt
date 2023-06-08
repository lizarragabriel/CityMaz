package com.lizarragabriel.mynode.api

import java.io.Serializable

data class City(var _id: String? = null, val name: String, val image: String, val description: String): Serializable
