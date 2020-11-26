package com.orangesoft.addressbook.data

import java.io.Serializable

data class AggregatedContact(
    val name: String = "",
    val mainPhone: String,
    val phones: List<Phone>,
    val photoUri: String?
) : Serializable