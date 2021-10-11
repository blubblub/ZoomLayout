import io.deepmedia.tools.publisher.common.License
import io.deepmedia.tools.publisher.common.Release

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("io.deepmedia.tools.publisher")
    id("maven-publish")
}

android {
    setCompileSdkVersion(property("compileSdkVersion") as Int)

    defaultConfig {
        setMinSdkVersion(property("minSdkVersion") as Int)
        setTargetSdkVersion(property("targetSdkVersion") as Int)
        versionName = "1.8.0"
        versionNameSuffix = ""
    }

    buildTypes {
        get("release").consumerProguardFile("proguard-rules.pro")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/library_release.kotlin_module")
    }
}


dependencies {
    val kotlinVersion = property("kotlinVersion") as String
    api("androidx.annotation:annotation:1.1.0")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    api("com.otaliastudios.opengl:egloo:0.6.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

publisher {
    // Bintray publications are not allowed anymore
    /*
    bintray {
        auth.user = "BINTRAY_USER"
        auth.key = "BINTRAY_KEY"
        auth.repo = "BINTRAY_REPO"
    }
    */
    project.group = "com.otaliastudios"
    project.artifact = "zoomlayout"
    project.description = "A collection of Android components that support zooming and panning of View hierarchies, images, video streams, and much more."
    project.url = "https://github.com/natario1/ZoomLayout"
    project.vcsUrl = "https://github.com/natario1/ZoomLayout.git"
    project.addLicense(License.APACHE_2_0)
    release.sources = Release.SOURCES_AUTO
    release.docs = Release.DOCS_AUTO
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release"){
                from(components["release"])
                groupId = "com.github.blubblub"
                artifactId = "ZoomLayout"
                version = "1.9"
            }
        }
    }
}
