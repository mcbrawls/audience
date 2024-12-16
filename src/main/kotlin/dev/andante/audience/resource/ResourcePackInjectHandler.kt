package dev.andante.audience.resource

import io.javalin.Javalin
import io.javalin.http.Context
import net.mcbrawls.inject.fabric.InjectFabric
import net.mcbrawls.inject.javalin.InjectJavalinFactory

object ResourcePackInjectHandler {
    private val resourcePacks: MutableMap<String, ByteArray> = mutableMapOf()

    fun createJavalin(): Javalin {
        return InjectJavalinFactory.create(InjectFabric.INSTANCE).apply {
            get("/packs/{hash}", ::handleRequest)
        }
    }

    fun handleRequest(context: Context) {
        val hash = context.pathParam("hash")

        val pack = resourcePacks[hash]
        if (pack == null) {
            context.status(404)
            return
        }

        context.result(pack)
    }

    /**
     * Adds a resource pack to be served.
     */
    fun add(vararg packs: ByteResourcePack) {
        packs.forEach { pack ->
            resourcePacks[pack.hash] = pack.bytes
        }
    }

    /**
     * Removes a resource pack from service.
     * @return whether a pack was removed
     */
    fun remove(pack: ByteResourcePack): Boolean {
        return remove(pack.hash)
    }

    /**
     * Removes a resource pack from service.
     * @return whether a pack was removed
     */
    fun remove(hash: String): Boolean {
        return resourcePacks.remove(hash) != null
    }
}
