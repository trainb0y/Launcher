import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization") version "1.7.20"
	id("org.jetbrains.compose")
}

group = "io.github.trainb0y"
version = "1.0-SNAPSHOT"

repositories {
	google()
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
	val ktorVersion = "2.1.2"
	val msalVersion = "1.13.2"

	implementation(compose.desktop.currentOs)

	implementation("me.nullicorn:ms-to-mca:0.0.1")

	implementation("com.microsoft.azure:msal4j:$msalVersion")
	//implementation("com.microsoft.aad.msal4j:$msalVersion")

	implementation("io.ktor:ktor-client-core:$ktorVersion")
	implementation("io.ktor:ktor-client-cio:$ktorVersion")
	implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

compose.desktop {
	application {
		mainClass = "MainKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "Launcher"
			packageVersion = "1.0.0"
		}
	}
}
