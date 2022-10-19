package io.github.trainb0y.launcher

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import me.nullicorn.msmca.minecraft.MinecraftAuth
import me.nullicorn.msmca.minecraft.MinecraftToken

suspend fun login(clientId: String): MinecraftToken {
	// this is a complete mess of hacky taped-together code
	// that provies the bare minimum for getting a microsoft token, with 0 error handling,
	// and shoves it into ms-to-mca and hopes and prays that it works.
	//
	// Good enough for now:tm:

	val client = HttpClient {
		install(ContentNegotiation) {
			json(Json {
				prettyPrint = true
				isLenient = true
			})
		}
	}

	val redirectUri = "https://localhost/microstupid_auth"

	println("https://login.live.com/oauth20_authorize.srf?client_id=$clientId&response_type=code&redirect_uri=$redirectUri&scope=XboxLive.signin%20offline_access")

	val msCode = readln().split("code=")[1]

	val msToken = Json.parseToJsonElement(
		client.submitForm(
			url = "https://login.live.com/oauth20_token.srf",
			formParameters = Parameters.build {
				append("client_id", clientId)
				append("redirect_uri", redirectUri)
				append("code", msCode)
				append("grant_type", "authorization_code")
			},
			// encodeInQuery = true

		).bodyAsText()
	).jsonObject["access_token"].toString()

	return MinecraftAuth().loginWithMicrosoft(msToken)
}