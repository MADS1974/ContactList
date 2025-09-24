package br.edu.scl.ifsp.mads.contactlist.view

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.mads.contactlist.R
import br.edu.scl.ifsp.mads.contactlist.adapter.ContactAdapter
import br.edu.scl.ifsp.mads.contactlist.databinding.ActivityMainBinding
import br.edu.scl.ifsp.mads.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.mads.contactlist.model.Constant.EXTRA_VIEW_CONTACT
import br.edu.scl.ifsp.mads.contactlist.model.Contact

class MainActivity : androidx.appcompat.app.AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val contactList: MutableList<Contact> = mutableListOf()

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            this,
            contactList
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_list)

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                contact?.also { newOrEditedContact ->
                    if (contactList.any { it.id == newOrEditedContact.id }) {
                        val position = contactList.indexOfFirst { it.id == newOrEditedContact.id }
                        contactList[position] = newOrEditedContact
                    } else {
                        contactList.add(newOrEditedContact)
                    }
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }

        fillContats()

        amb.contactsLv.adapter = contactAdapter

        registerForContextMenu(amb.contactsLv)

        amb.contactsLv.setOnItemClickListener { _, _, position, _ ->
            val contact = contactList[position]
            val viewContactIntent = Intent(this, ContactActivity::class.java)
            viewContactIntent.putExtra(EXTRA_CONTACT, contact)
            viewContactIntent.putExtra(EXTRA_VIEW_CONTACT, true)
            startActivity(viewContactIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addContactMi -> {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId) {
            R.id.removeContactMi -> {
                contactList.removeAt(position)
                contactAdapter.notifyDataSetChanged()
                Toast.makeText(this, getString(R.string.contato_removido), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.editContactMi -> {
                val contact = contactList[position]
                val editContactIntent = Intent(this, ContactActivity::class.java)
                editContactIntent.putExtra(EXTRA_CONTACT, contact)
                carl.launch(editContactIntent)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterForContextMenu(amb.contactsLv)
    }

    private fun fillContats() {
        for (i in 1..10) {
            contactList.add(
                Contact(
                    i,
                    "Nome $i",
                    "Endereço $i",
                    "Telefone (81) 91234567-$i",
                    "Email $i"
                )
            )
        }
    }
}