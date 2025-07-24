package dev.andante.audience

import net.minecraft.particle.ParticleEffect
import net.minecraft.util.math.Vec3d

data class ParticleBuilder<T : ParticleEffect>(
    val effect: T,
    val count: Int = 1,
    val speed: Double = 1.0,
    val size: Vec3d = Vec3d.ZERO,
    val force: Boolean = false,
    val important: Boolean = false,
)
