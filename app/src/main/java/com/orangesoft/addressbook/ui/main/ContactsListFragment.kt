package com.orangesoft.addressbook.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orangesoft.addressbook.R
import com.orangesoft.addressbook.data.AggregatedContact
import com.orangesoft.addressbook.viewmodel.ContactsListViewModel

class ContactsListFragment : Fragment() {

    private lateinit var viewModel: ContactsListViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contacts_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.contacts_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val permissionCheck =
            checkSelfPermission(this.requireContext(), Manifest.permission.READ_CONTACTS)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_REQUEST_CODE)
        } else {
            loadContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                loadContacts()
            }else{
                Toast.makeText(this.context, "Permission not granted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadContacts() {
        val contactsAdapter = ContactsListAdapter { aggregatedContact -> adapterOnClick(aggregatedContact) }
        recyclerView.adapter = contactsAdapter
        viewModel = ViewModelProviders.of(this).get(ContactsListViewModel::class.java)
        viewModel.contacts.observe(
            this.viewLifecycleOwner,
            Observer<ArrayList<AggregatedContact>> { contacts ->
                contactsAdapter.submitList(contacts)
            })
    }

    private fun adapterOnClick(contact: AggregatedContact) {
        val bundle = Bundle()
        bundle.putSerializable(CONTACT_KEY, contact)
        findNavController().navigate(R.id.action_contactsListFragment_to_contactDetailsFragment, bundle)
    }

    companion object {
        const val CONTACTS_REQUEST_CODE = 1
        const val CONTACT_KEY = "contact_key"
    }

}
