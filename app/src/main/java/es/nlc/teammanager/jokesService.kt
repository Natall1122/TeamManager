package es.nlc.teammanager

import es.nlc.teammanager.clases.Jokes
import retrofit2.http.GET

interface jokesService {
    @GET("Programming?format=txt&amount=10")
    suspend fun getJokes(): List<Jokes>
}