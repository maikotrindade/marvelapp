package io.github.maikotrindade.marvelapp.characters.domain.api

import io.github.maikotrindade.marvelapp.api.NetworkFactory
import io.github.maikotrindade.marvelapp.characters.domain.model.CharactersResponse
import io.github.maikotrindade.marvelapp.characters.domain.model.ComicsResponse
import io.github.maikotrindade.marvelapp.characters.domain.model.SeriesResponse
import io.github.maikotrindade.marvelapp.util.basePagination
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object CharactersService {

    fun requestCharacters(ts: String, apikey: String, hash: String, offset: Int): Flowable<Response<CharactersResponse>> {
        return NetworkFactory.buildService(CharactersInterface::class.java)
            .getCharacters(ts, apikey, hash, offset, basePagination)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestComics(ts: String, apikey: String, hash: String, id: String): Flowable<Response<ComicsResponse>> {
        return NetworkFactory.buildService(CharactersInterface::class.java)
            .getCharactersComics(id, ts, apikey, hash)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestSeries(ts: String, apikey: String, hash: String, id: String): Flowable<Response<SeriesResponse>> {
        return NetworkFactory.buildService(CharactersInterface::class.java)
            .getCharactersSeries(id, ts, apikey, hash)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}

interface CharactersInterface {

    @GET("/v1/public/characters?orderBy=name&")
    fun getCharacters(
        @Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String,
        @Query("offset") offset: Int, @Query("limit") limit: Int
    ): Flowable<Response<CharactersResponse>>

    @GET("/v1/public/characters/{id}/comics?")
    fun getCharactersComics(@Path("id") id: String, @Query("ts") ts: String,
                            @Query("apikey") apikey: String, @Query("hash") hash: String
    ): Flowable<Response<ComicsResponse>>

    @GET("/v1/public/characters/{id}/series?")
    fun getCharactersSeries(@Path("id") id: String, @Query("ts") ts: String,
                            @Query("apikey") apikey: String, @Query("hash") hash: String
    ): Flowable<Response<SeriesResponse>>

}