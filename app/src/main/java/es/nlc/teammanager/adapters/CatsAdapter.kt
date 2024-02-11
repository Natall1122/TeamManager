package es.nlc.teammanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nlc.teammanager.R

class CatsAdapter (private val context: Context?,
                      private val cat: MutableList<String>):
    RecyclerView.Adapter<CatsAdapter.CatsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_cats, parent, false)
        return CatsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cat.size
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val cats = cat[position]
        holder.bindItem(cats)
    }


    class CatsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val imageCat = view.findViewById<ImageView>(R.id.ImageCats)

        fun bindItem(message: String){
            Picasso.get().load(message).into(imageCat)
        }
    }
}