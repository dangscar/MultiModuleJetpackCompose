package com.nlhd.network

import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.character.CharacterPage
import com.nlhd.network.domain.models.episode.Episode
import com.nlhd.network.domain.models.episode.EpisodePage
import com.nlhd.network.remote.character.RemoteCharacter
import com.nlhd.network.remote.character.RemoteCharacterPage
import com.nlhd.network.remote.character.toDomainCharacter
import com.nlhd.network.remote.character.toDomainCharacterPage
import com.nlhd.network.remote.episode.RemoteEpisode
import com.nlhd.network.remote.episode.RemoteEpisodePage
import com.nlhd.network.remote.episode.toDomainEpisode
import com.nlhd.network.remote.episode.toDomainEpisodePage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient(CIO) {
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }
        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private var characterCache = mutableMapOf<Int, Character>()

    suspend fun getCharacter(id: Int): ApiOperation<Character> {
        characterCache[id]?.let {
            return ApiOperation.Success(it)
        }
        return safeApiCall {
            client.get("character/$id").body<RemoteCharacter>().toDomainCharacter().also {
                characterCache[id] = it
            }
        }
    }

    suspend fun getEpisodes(episodeId: List<Int>): ApiOperation<List<Episode>> {
        return if (episodeId.size == 1) {
            getEpisode(episodeId[0]).mapSuccess {
                listOf(it)
            }
        } else {
            safeApiCall {
                val idsCommaSeparated = episodeId.joinToString(",") //Chuyển đổi mảng thành chuỗi
                client.get("episode/$idsCommaSeparated").body<List<RemoteEpisode>>().map {
                    it.toDomainEpisode()
                }
            }
        }


    }

    suspend fun getEpisode(episodeId: Int): ApiOperation<Episode> {
        return safeApiCall {
            client.get("episode/$episodeId").body<RemoteEpisode>().toDomainEpisode()
        }
    }

    suspend fun getAllCharacterByPage(page: Int): CharacterPage {
        return client.get("character?page=$page").body<RemoteCharacterPage>().toDomainCharacterPage()
    }

    suspend fun getAllEpisodeByPage(page: Int): EpisodePage {
        return client.get("episode") {
            url {
                parameters.append("page", page.toString())
            }
        }.body<RemoteEpisodePage>().toDomainEpisodePage()
    }


    private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
        return try {
            ApiOperation.Success(data = apiCall())
        } catch (e: Exception) {
            ApiOperation.Failure(e)
        }
    }

}

sealed interface ApiOperation<T> {
    data class Success<T>(val data: T): ApiOperation<T>
    data class Failure<T>(val exception: Exception): ApiOperation<T>

    fun onSuccess (block: (T) -> Unit): ApiOperation<T> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    fun onFailure(block: (Exception) -> Unit): ApiOperation<T> {
        if (this is Failure) {
            block(exception)
        }
        return this
    }

    fun <R> mapSuccess(transform: (T) -> R): ApiOperation<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> Failure(exception)
        }
    }
}
