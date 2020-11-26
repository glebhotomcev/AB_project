package com.orangesoft.addressbook.data

import java.io.Serializable

data class Contact(
    val id: Long = 0,
    val name: String = "",
    val phone: String,
    val phoneType: Int,
    val photoUri: String? = null
): Serializable