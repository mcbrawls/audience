package dev.andante.audience.resource

import com.google.common.hash.Hashing

/**
 * A complete resource pack.
 */
data class BuiltResourcePack(
    /**
     * All bytes composting the resource pack.
     */
    val bytes: ByteArray
) {
    /**
     * The hash of the resource pack's bytes as a string.
     */
    val hash: String by lazy { hashSha1(bytes).lowercase() }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BuiltResourcePack

        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    companion object {
        /**
         * Hashes a byte array to a sha1 string.
         */
        @Suppress("DEPRECATION")
        private fun hashSha1(bytes: ByteArray): String {
            return Hashing.sha1().hashBytes(bytes).toString()
        }
    }
}
