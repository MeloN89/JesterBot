package jesterbot.input

import jesterbot.util.VectorUtils

class CarData(playerInfo: rlbot.flat.PlayerInfo, val elapsedSeconds: Float) {
    val position = VectorUtils.convertFromRLVector(playerInfo.physics().location())
    val velocity = VectorUtils.convertFromRLVector(playerInfo.physics().velocity())
    val orientation = CarOrientation.convert(playerInfo.physics().rotation())
    val boost = playerInfo.boost().toDouble()
    val hasWheelContact = playerInfo.hasWheelContact()
    val isSupersonic = playerInfo.isSupersonic
    val team = playerInfo.team()
}
