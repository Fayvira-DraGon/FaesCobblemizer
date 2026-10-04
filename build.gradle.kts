import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val deleteFiles by tasks.registering(Delete::class) {
  description = "delete existing configuration"
  delete(file(".idea/runConfigurations"))
}

tasks.named("ideaSyncTask") {
  dependsOn(deleteFiles)
}

plugins {
  id("java")
  id("dev.architectury.loom") version ("1.13-SNAPSHOT")
  id("architectury-plugin") version ("3.4-SNAPSHOT")
  kotlin("jvm") version "2.3.10"
}

group = "${project.property("mod_group")}"
version = "${project.property("mod_version")}"

architectury {
  platformSetupLoomIde()
  fabric()
}

loom {
  silentMojangMappingsLicense()

  @Suppress("UnstableApiUsage")
  mixin {
    defaultRefmapName.set("mixins.${project.property("mod_id")}.refmap.json")
  }

  runs {
    named("client") {
      client()
      property("mixin.debug.export", "true")
      property("mixin.dumpTargetOnFailure", "true")
      property("devauth.enabled", "true") // devauth: enable
      property("devauth.account", "main") // account type: minecraft
      property("mixin.env.refMapRemappingFile", "${projectDir}/build/createSrgToMcp/output.srg") //emi
      property("fabric-tag-conventions-v2.missingTagTranslationWarning", "VERBOSE") // see individual untranslated item tags
      programArg("--width=${project.property("window_width")}")
      programArg("--height=${project.property("window_height")}")
      ideConfigGenerated(true)
    }
    named("server") {
      property("fabric-tag-conventions-v2.missingTagTranslationWarning", "VERBOSE")
      ideConfigGenerated(true)
    }
  }
}

repositories {
  mavenCentral()
  maven("https://artefacts.cobblemon.com/releases/") { name = "Cobblemon" }
  maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") { name = "DevAuth" }
}

dependencies {
  implementation(kotlin("stdlib-${project.property("kotlin_stdlib")}"))
  minecraft("net.minecraft:minecraft:${project.property("minecraft_version")}")
  mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
  modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")

  modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.property("devauth_version")}")

  modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
  modCompileOnly(fabricApi.module("${project.property("fabric_command_api_version")}", "${project.property("fabric_api_version")}"))
  modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_version")}")

  modCompileOnly("com.cobblemon:mod:${project.property("cobblemon_version")}") { isTransitive = false }
  modImplementation("com.cobblemon:fabric:${project.property("cobblemon_version")}")

  testImplementation("org.junit.jupiter:junit-jupiter-api:${project.property("junit-jupiter-api_version")}")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.property("junit-jupiter-engine_version")}")
}

// sourceSets {
//   main {
//     java {
//       exclude( /* ...excludes = */
//         ""
//       )
//     }
//     resources {
//       exclude( /* ...excludes = */
//       )
//     }
//   }
// }

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}

java {
  withSourcesJar()

  sourceCompatibility = JavaVersion.toVersion((project.property("java_version") as String).toInt())
  targetCompatibility = JavaVersion.toVersion((project.property("java_version") as String).toInt())
}

kotlin {
  compilerOptions {
    freeCompilerArgs.add("-Xannotation-target-all")
  }
}

tasks.compileJava {
  options.release = (project.property("java_version") as String).toInt()
}

tasks.compileKotlin {
  compilerOptions {
    // jvmTarget.set(JvmTarget.fromTarget("JVM_" + project.property("java_version")))
    jvmTarget.set(JvmTarget.JVM_21)
  }
}

tasks.processResources {
  inputs.property("version", project.version)

  filesMatching("fabric.mod.json") {
    expand(project.properties)
  }
}

tasks.withType<AbstractArchiveTask> {
  archiveBaseName = "${project.property("mod_archives_name")}"
  archiveVersion = "${project.property("mod_version")}"
}