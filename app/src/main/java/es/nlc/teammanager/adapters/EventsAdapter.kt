package es.nlc.teammanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.nlc.teammanager.R
import es.nlc.teammanager.clases.events

class EventsAdapter(private val context: Context?,
                      private val events: MutableList<events>,
                      private val mListener: (events) -> Unit) :
    RecyclerView.Adapter<EventsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_events, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val event = events[position]
        holder.bindItem(event)
        holder.itemView.setOnClickListener { mListener(event) }
    }


    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val dia: TextView = view.findViewById(R.id.dia)
        private val hacabar: TextView = view.findViewById(R.id.hacabar)
        private val si: TextView = view.findViewById(R.id.si)
        private val no: TextView = view.findViewById(R.id.no)
        private val hcomençar: TextView = view.findViewById(R.id.hcomençar)
        private val nom: TextView = view.findViewById(R.id.nom)
        private val dubte: TextView = view.findViewById(R.id.dubte)

        fun bindItem(e: events){
            dia.text = e.dia
            nom.text = e.nom
            si.text = e.si.toString()
            no.text = e.no.toString()
            hacabar.text = e.hacabar
            hcomençar.text = e.hcomençar
            dubte.text = e.dubte.toString()
        }
    }
}