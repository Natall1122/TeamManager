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
                      private val products: MutableList<events>,
                      private val mListener: (events) -> Unit) :
    RecyclerView.Adapter<EventsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_events, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.bindItem(product)
        holder.itemView.setOnClickListener { mListener(product) }
    }


    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val dia: TextView = view.findViewById(R.id.dia)
        private val hacabar: TextView = view.findViewById(R.id.hacabar)
        private val si: ImageView = view.findViewById(R.id.si)
        private val no: TextView = view.findViewById(R.id.no)
        private val hcomençar: TextView = view.findViewById(R.id.hcomençar)
        private val nom: ImageView = view.findViewById(R.id.nom)

        fun bindItem(p: events){
            tvName.text = p.name
            tvPrice.text = p.city
            tvName.text = p.name
            tvPrice.text = p.city
            tvName.text = p.name
            tvPrice.text = p.city
            tvName.text = p.name
            tvPrice.text = p.city
        }
    }
}