package com.orangesoft.addressbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.orangesoft.addressbook.R
import com.orangesoft.addressbook.data.AggregatedContact
import com.orangesoft.addressbook.ui.main.ContactsListFragment.Companion.CONTACT_KEY

class ContactDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarView: ImageView = view.findViewById(R.id.avatar)
        val nameView: TextView = view.findViewById(R.id.name)
        val phones: TextView = view.findViewById(R.id.all_phones)

        val aggregatedContact = arguments?.getSerializable(CONTACT_KEY) as AggregatedContact
        Glide.with(view).load(aggregatedContact.photoUri)
            .placeholder(R.drawable.ic_launcher_background)
            .into(avatarView)
        nameView.text = aggregatedContact.name
        val allPhones = aggregatedContact.phones.joinToString { phone -> "${phone.type} - ${phone.number}\n" }
        phones.text = allPhones
    }
}
