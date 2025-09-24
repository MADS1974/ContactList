package br.edu.scl.ifsp.mads.contactlist.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.mads.contactlist.R
import br.edu.scl.ifsp.mads.contactlist.databinding.ActivityContactBinding
import br.edu.scl.ifsp.mads.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.mads.contactlist.model.Constant.EXTRA_VIEW_CONTACT
import br.edu.scl.ifsp.mads.contactlist.model.Contact

class ContactActivity : androidx.appcompat.app.AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        val receivedContact = intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
        receivedContact?.let { received ->
            val viewContact = intent.getBooleanExtra(EXTRA_VIEW_CONTACT, false)
            with(acb) {
                if (viewContact) {
                    nameEt.isEnabled = false
                    addressEt.isEnabled = false
                    phoneEt.isEnabled = false
                    emailEt.isEnabled = false
                    saveBt.visibility = GONE
                }
                nameEt.setText(received.name)
                addressEt.setText(received.address)
                phoneEt.setText(received.phone)
                emailEt.setText(received.email)
            }
        }

        with(acb) {
            saveBt.setOnClickListener {
                val contact = Contact(
                    id = receivedContact?.id ?: hashCode(),
                    name = nameEt.text.toString(),
                    address = addressEt.text.toString(),
                    phone = phoneEt.text.toString(),
                    email = emailEt.text.toString()
                )

                val resultIntent = Intent().apply {
                    putExtra(EXTRA_CONTACT, contact)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}