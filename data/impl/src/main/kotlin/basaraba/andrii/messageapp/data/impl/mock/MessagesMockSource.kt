package basaraba.andrii.messageapp.data.impl.mock

import android.content.res.AssetManager
import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.impl.model.MessagesMock
import basaraba.andrii.messageapp.data.impl.model.toDomain
import kotlinx.serialization.json.Json

internal interface MessagesMockSource {
    suspend fun getMockMessages(): List<MessageDomain>
}

internal class MessagesMockSourceImpl(
    private val assets: AssetManager,
) : MessagesMockSource {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    override suspend fun getMockMessages(): List<MessageDomain> =
        getMessagesFromAssets().map { it.toDomain() }


    private fun getMessagesFromAssets(): List<MessagesMock> =
        try {
            val stream = assets.open("mock_messages.json")
            val response = stream.bufferedReader().use { it.readText() }
            json.decodeFromString<List<MessagesMock>>(response)
        } catch (e: Exception) {
            val message = "Failed to load mock asset: mock_messages.json"
            throw IllegalStateException(message, e)
        }
}
