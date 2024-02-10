package es.nlc.teammanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.nlc.teammanager.R
import es.nlc.teammanager.clases.Jokes

class JokesAdapter(private val context: Context?, private val jokes: MutableList<Jokes>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_jokes, parent, false)
        return JokesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bindItem(joke)
    }

    inner class JokesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val setupText = view.findViewById<TextView>(R.id.acudit)
        private val deliveryText = view.findViewById<TextView>(R.id.joke_part2)

        fun bindItem(joke: Jokes) {
            setupText.text = joke.setup ?: joke.joke // Utilitzem el camp setup si és un acudit de dues parts, sinó utilitzem el camp joke
            deliveryText.text = joke.delivery
            deliveryText.visibility = if (joke.type == "twopart") View.VISIBLE else View.GONE
        }
    }
}
