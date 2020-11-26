package com.orangesoft.addressbook.data

import java.io.Serializable

data class Phone(
    val number: String,
    val type: Int
) : Serializable