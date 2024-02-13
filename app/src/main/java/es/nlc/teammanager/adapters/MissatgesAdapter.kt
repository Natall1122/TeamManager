package es.nlc.teammanager.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import es.nlc.teammanager.R
import es.nlc.teammanager.clases.Missatges

class MissatgesAdapter(
    private val context: Context,
    private val items: MutableList<Missatges>
) : RecyclerView.Adapter<MissatgesAdapter.ViewHolder>() {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val color = sharedPreferences.getString("colorXat", "#D8E9A8")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.missatge_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
        holder.itemView.setBackgroundColor(Color.parseColor(color))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvnom: TextView = view.findViewById(R.id.tvNom)
        private val tvContent: TextView = view.findViewById(R.id.tvMissatge)

        fun bindItem(item: Missatges) {
            tvnom.text = item.nom
            tvContent.text = item.missatge
        }
    }
}
