package es.nlc.teammanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import es.nlc.teammanager.R
import es.nlc.teammanager.clases.galeria

class GaleriaAdapter(private val context: Context?,
                     private val images: MutableList<galeria>,
                     private val mListener: (galeria) -> Unit) :
    RecyclerView.Adapter<GaleriaAdapter.ImagesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_gal, parent, false)
        return ImagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = images[position]
        holder.bindItem(image)
        holder.itemView.setOnClickListener { mListener(image) }
    }


    class ImagesViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val image: ImageView = view.findViewById(R.id.images)

        fun bindItem(p: galeria){
            image.setImageResource(p.image)
        }
    }
}