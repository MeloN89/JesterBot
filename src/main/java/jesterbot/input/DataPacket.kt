package jesterbot.input

import rlbot.flat.GameTickPacket
import rlbot.flat.PlayerInfo

class DataPacket(request: GameTickPacket, playerInfo: PlayerInfo) {
    val car = CarData(playerInfo, request.gameInfo().secondsElapsed())
    val ball = BallData(request.ball())
    val team = playerInfo.team()
}
