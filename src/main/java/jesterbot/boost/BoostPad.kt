package jesterbot.boost

import org.joml.Vector3f

data class BoostPad(val location: Vector3f,
                    val isFullBoost: Boolean,
                    var isActive: Boolean = false)