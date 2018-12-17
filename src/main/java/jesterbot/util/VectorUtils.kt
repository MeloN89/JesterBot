package jesterbot.util

import org.joml.Vector2f
import org.joml.Vector3f
import rlbot.flat.Vector3

object VectorUtils {

    fun flatten(vector: Vector3f): Vector2f {
        return Vector2f(vector.x, vector.y)
    }

    fun convertFromRLVector(vector: rlbot.flat.Vector3): Vector3f {
        return Vector3f(vector.x(), vector.y(), vector.z())
    }

    fun convertFromRLVector(vector: rlbot.vector.Vector3): Vector3f {
        return Vector3f(vector.y, vector.x, vector.z)
    }

    fun toRLBotVector(vector: Vector3f): rlbot.vector.Vector3 {
        // Invert the X value again so that rlbot sees the format it expects.
        return rlbot.vector.Vector3(-vector.x, vector.y, vector.z)
    }
}
