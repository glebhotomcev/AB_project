package com.orangesoft.addressbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.orangesoft.addressbook.data.AggregatedContact
import com.orangesoft.addressbook.data.Phone
import com.orangesoft.addressbook.provider.SystemContactsProvider
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ContactsListViewModel : ViewModel(), KoinComponent {

    private val contactsProvider: SystemContactsProvider by inject()

    val contacts: LiveData<ArrayList<AggregatedContact>> = liveData {
        val data = contactsProvider.collectContacts()
        val groupedContacts = data.groupBy { contact -> contact.id }

        val aggregatedContacts = ArrayList<AggregatedContact>()

        groupedContacts.forEach { entry ->
            val contacts = entry.value
            val phones = contacts.map { contact -> Phone(contact.phone, contact.phoneType) }
            aggregatedContacts.add(AggregatedContact(contacts[0].name, contacts[0].phone, phones, contacts[0].photoUri))
        }

        emit(aggregatedContacts)
    }
}

