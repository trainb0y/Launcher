package io.github.trainb0y.launcher

import com.microsoft.aad.msal4j.IAuthenticationResult
import com.microsoft.aad.msal4j.InteractiveRequestParameters
import com.microsoft.aad.msal4j.PublicClientApplication
import com.nimbusds.oauth2.sdk.Scope
import me.nullicorn.msmca.minecraft.MinecraftAuth
import me.nullicorn.msmca.minecraft.MinecraftToken
import java.net.URI


object Auth {
	// todo: https://learn.microsoft.com/en-us/azure/active-directory/develop/msal-java-token-cache-serialization
	// todo: https://learn.microsoft.com/en-us/azure/active-directory/develop/scenario-desktop-acquire-token?tabs=java

	val redirectUri = URI("http://localhost:4321/")
	val clientId = System.getenv("MICROSOFT_CLIENT_ID") //todo: replace with something in launcher config

	fun getMicrosoftToken(): String {
		val publicClientApplication: PublicClientApplication = PublicClientApplication
			.builder(clientId)
			.build()

		val parameters: InteractiveRequestParameters = InteractiveRequestParameters
			.builder(redirectUri)
			.scopes(setOf("XboxLive.signin"))
			.build()

		val result: IAuthenticationResult = publicClientApplication.acquireToken(parameters).join()
		return result.accessToken()
	}

	fun getMinecraftToken(): MinecraftToken {
		return MinecraftAuth().loginWithMicrosoft(getMicrosoftToken())
	}
}

fun main() {
	println(Auth.getMinecraftToken())
}