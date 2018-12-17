package jesterbot.input

import jesterbot.util.VectorUtils
import rlbot.flat.BallInfo

class BallData(ball: BallInfo) {
    val position = VectorUtils.convertFromRLVector(ball.physics().location())
    val velocity = VectorUtils.convertFromRLVector(ball.physics().velocity())
    val spin = VectorUtils.convertFromRLVector(ball.physics().angularVelocity())
}
