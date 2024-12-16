package dev.andante.audience

import dev.andante.audience.resource.ResourcePackInjectHandler
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.MinecraftServer

object AudienceInitializer : ModInitializer {
    private lateinit var _minecraftServer: MinecraftServer

    /**
     * The Minecraft server instance.
     */
    val server: MinecraftServer get() = _minecraftServer

    override fun onInitialize() {
        val javalin = ResourcePackInjectHandler.createJavalin()

        // register event to capture server
        ServerLifecycleEvents.SERVER_STARTING.register { server ->
            _minecraftServer = server
            javalin.start(server.serverPort)
        }

        ServerLifecycleEvents.SERVER_STOPPING.register {
            javalin.stop()
        }
    }
}
