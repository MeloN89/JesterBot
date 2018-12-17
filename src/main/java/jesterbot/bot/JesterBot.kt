package jesterbot.bot

import jesterbot.boost.BoostManager
import jesterbot.input.DataPacket
import jesterbot.output.ControlsOutput
import jesterbot.util.VectorUtils
import jesterbot.util.VectorUtils.flatten
import jesterbot.util.VectorUtils.toRLBotVector
import org.joml.Vector2f
import rlbot.Bot
import rlbot.ControllerState
import rlbot.flat.GameTickPacket
import rlbot.manager.BotLoopRenderer

import java.awt.*

class JesterBot(private val playerIndex: Int) : Bot {

    private val boost = BoostManager()

    private fun internalProcessInput(input: DataPacket): ControlsOutput {

        val myCar = input.car
        val ballPosition = flatten(input.ball.position)
        val carPosition = flatten(myCar.position)
        val carDirection = flatten(myCar.orientation.noseVector)
        val carToBall = ballPosition.sub(carPosition)

        var currentRad = Math.atan2(carDirection.y.toDouble(), carDirection.x.toDouble())
        var idealRad = Math.atan2(carToBall.y.toDouble(), carToBall.x.toDouble())

        if (Math.abs(currentRad - idealRad) > Math.PI) {
            if (currentRad < 0) {
                currentRad += Math.PI * 2
            }
            if (idealRad < 0) {
                idealRad += Math.PI * 2
            }
        }

        val steerCorrectionRadians = idealRad - currentRad
        val steer = if (steerCorrectionRadians > 0) {
            -1f
        } else {
            1f
        }

        // Change this to true if you want to try the rendering feature!
        if (true) {
            val renderer = BotLoopRenderer.forBotLoop(this)
            val carPosRL = toRLBotVector(myCar.position)
            val ballPosRL = toRLBotVector(input.ball.position)
            renderer.drawLine3d(Color.BLUE, carPosRL, ballPosRL)
            renderer.drawString3d("It's me", Color.green, carPosRL, 2, 2)
            renderer.drawCenteredRectangle3d(Color.black, ballPosRL, 20, 20, false)
        }

        return ControlsOutput()
                .steer(steer)
                .throttle(1f)
    }

    override fun getIndex(): Int {
        return this.playerIndex
    }

    override fun processInput(packet: GameTickPacket): ControllerState {
        if (packet.playersLength() <= playerIndex || packet.ball() == null) {
            return ControlsOutput()
        }
        boost.loadGameTickPacket(packet)
        val playerInfo = packet.players(playerIndex)
        val dataPacket = DataPacket(packet, playerInfo)
        return internalProcessInput(dataPacket)
    }

    override fun retire() {
        println("Retiring JesterBot: $playerIndex")
    }
}
