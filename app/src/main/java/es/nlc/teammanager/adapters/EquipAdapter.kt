package es.nlc.teammanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.nlc.teammanager.R
import es.nlc.teammanager.clases.Equip

class EquipAdapter (private val context: Context?,
                    private val equip: MutableList<Equip>,
                    private val mListener: (Equip) -> Unit) :
    RecyclerView.Adapter<EquipAdapter.EquipViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_equip, parent, false)
        return EquipViewHolder(view)
    }

    override fun getItemCount(): Int {
        return equip.size
    }

    override fun onBindViewHolder(holder: EquipViewHolder, position: Int) {
        val jugador = equip[position]
        holder.bindItem(jugador)
        holder.itemView.setOnClickListener { mListener(jugador) }
    }


    class EquipViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val cara: ImageView = view.findViewById(R.id.image)
        private val posicio: TextView = view.findViewById(R.id.posicio)
        private val nom: TextView = view.findViewById(R.id.nom)

        fun bindItem(e: Equip){
            cara.setImageResource(e.cara)
            nom.text = e.nom
            posicio.text = e.posicio

        }
    }
}