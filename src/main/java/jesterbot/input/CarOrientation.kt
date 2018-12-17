package jesterbot.input

import org.joml.Vector3f
import rlbot.flat.Rotator

class CarOrientation(val noseVector: Vector3f,
                     val roofVector: Vector3f,
                     val rightVector: Vector3f) {

    companion object {
        fun convert(rotation: Rotator): CarOrientation {
            val pitch = rotation.pitch().toDouble()
            val yaw = rotation.yaw().toDouble()
            val roll = rotation.roll().toDouble()

            val noseX = -1.0 * Math.cos(pitch) * Math.cos(yaw)
            val noseY = Math.cos(pitch) * Math.sin(yaw)
            val noseZ = Math.sin(pitch)

            val roofX = Math.cos(roll) * Math.sin(pitch) * Math.cos(yaw) + Math.sin(roll) * Math.sin(yaw)
            val roofY = Math.cos(yaw) * Math.sin(roll) - Math.cos(roll) * Math.sin(pitch) * Math.sin(yaw)
            val roofZ = Math.cos(roll) * Math.cos(pitch)

            val nose = Vector3f(noseX.toFloat(), noseY.toFloat(), noseZ.toFloat())
            val roof = Vector3f(roofX.toFloat(), roofY.toFloat(), roofZ.toFloat())
            val right = nose.cross(roof)

            return CarOrientation(nose, roof, right)
        }
    }
}
