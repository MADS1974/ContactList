package br.edu.scl.ifsp.mads.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.mads.contactlist.R
import br.edu.scl.ifsp.mads.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.mads.contactlist.model.Contact

class ContactAdapter(
    context: Context,
    private val contactlist: MutableList<Contact>
) : android.widget.ArrayAdapter<Contact>(
    context,
    R.layout.tile_contact,
    contactlist
) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val contact = contactlist[position]

        var contactTileView = convertView

        if (contactTileView == null) {
            val tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root

            val tileContactHolder = TileContactHolder(tcb.nameTv, tcb.emailTv, tcb.phoneTv)
            contactTileView.tag = tileContactHolder
        }

        val holder = contactTileView.tag as TileContactHolder
        holder.nameTv.text = contact.name
        holder.emailTv.text = contact.email
        holder.phoneTv.text = contact.phone

        return contactTileView
    }

    private data class TileContactHolder(
        val nameTv: TextView,
        val emailTv: TextView,
        val phoneTv: TextView
    )
}