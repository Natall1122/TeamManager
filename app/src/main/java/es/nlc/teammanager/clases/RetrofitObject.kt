package es.nlc.teammanager.clases

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object{
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            synchronized(this){
                if(instance == null){
                    instance = Retrofit.Builder()
                        .baseUrl("https://v2.jokeapi.dev/joke/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance!!
            }
        }
    }
}