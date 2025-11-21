val deleteRunConfigs by tasks.registering(Delete::class) {
  delete(file(".idea/runConfigurations"))
}

tasks.named("ideaSyncTask") {
  dependsOn(deleteRunConfigs)
}

plugins {
  id("java")
  id("dev.architectury.loom") version ("1.9-SNAPSHOT")
  id("architectury-plugin") version ("3.4-SNAPSHOT")
  kotlin("jvm") version "2.2.10"
}

group = "${project.property("mod_group_id")}"
version = "${project.property("mod_version")}"

architectury {
  platformSetupLoomIde()
  fabric()
}

loom {
  silentMojangMappingsLicense()

  @Suppress("UnstableApiUsage")
  mixin {
    defaultRefmapName.set("mixins.${project.name}.refmap.json")
  }

  runs {
    named("client") {
      client()
      property("mixin.debug.export", "true")
      property("mixin.dumpTargetOnFailure", "true")
      property("devauth.enabled", "true") // devauth: enable
      property("devauth.account", "main") // account type: minecraft
      programArg("--width=${project.property("window_width")}")
      programArg("--height=${project.property("window_height")}")
      ideConfigGenerated(true)
    }
  }
}

repositories {
  mavenCentral()
  maven("https://api.modrinth.com/maven") { name = "Modrinth: Cobbreeding"}
  maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") { name = "GeckoLib"; content { includeGroup("software.bernie.geckolib") }; }
  maven("https://maven.impactdev.net/repository/development/") { name = "Cobblemon" }
  maven("https://oss.sonatype.org/content/repositories/snapshots") { name = "Cobblemon" }
  maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") { name = "DevAuth" }
}

dependencies {
  minecraft("net.minecraft:minecraft:${project.property("minecraft_version")}")
  mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}")
  modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")

  modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
  modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.property("devauth_version")}")
  modImplementation(fabricApi.module("${project.property("fabric_command_api_version")}", "${project.property("fabric_api_version")}"))

  modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_version")}")
  modImplementation("com.cobblemon:fabric:${project.property("cobblemon_version")}")
  modCompileOnly("maven.modrinth:cobbreeding:${project.property("cobbreeding_version")}") // cobbreeding

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
//         "/assets/cobblemizer/textures/item/background.png",
//         "/assets/cobblemizer/textures/item/background_max.png"
//       )
//     }
//   }
// }

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}

tasks.processResources {
  inputs.property("version", project.version)

  filesMatching("fabric.mod.json") {
    expand(project.properties)
  }
  // filesMatching("${project.property("mod_id")}.mixins.json") {
  //   expand(project.properties)
  // }
}

tasks.withType<AbstractArchiveTask> {
  archiveBaseName = "${project.property("mod_archives_name")}"
  archiveVersion = "${project.property("mod_version")}"
}