package jesterbot.bot

import jesterbot.output.ControlsOutput
import rlbot.Bot
import rlbot.ControllerState
import rlbot.flat.GameTickPacket

class DeadBot(private val playerIndex: Int) : Bot {
    override fun getIndex(): Int {
        return this.playerIndex
    }

    override fun processInput(packet: GameTickPacket): ControllerState {
        return ControlsOutput()
    }

    override fun retire() {
        println("Retiring dummy bot $playerIndex")
    }
}
