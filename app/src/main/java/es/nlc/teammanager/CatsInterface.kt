package es.nlc.teammanager

import es.nlc.teammanager.clases.Cats
import retrofit2.Response
import retrofit2.http.GET

interface CatsInterface {
    @GET("search?limit=10")
    suspend fun getCats(): Response<List<Cats>>

}