package com.orangesoft.addressbook.provider

import com.orangesoft.addressbook.data.Contact

interface ContactsProvider {

    fun collectContacts(): List<Contact>
}