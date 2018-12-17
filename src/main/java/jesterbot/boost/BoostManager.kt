package jesterbot.boost

import jesterbot.util.VectorUtils
import rlbot.cppinterop.RLBotDll
import rlbot.flat.FieldInfo
import rlbot.flat.GameTickPacket
import java.io.IOException
import java.util.*

class BoostManager {

    private val orderedBoosts = ArrayList<BoostPad>()
    private val fullBoosts = ArrayList<BoostPad>()
    private val smallBoosts = ArrayList<BoostPad>()

    @Synchronized
    private fun loadFieldInfo(fieldInfo: FieldInfo) {
        orderedBoosts.clear()
        fullBoosts.clear()
        smallBoosts.clear()

        for (i in 0 until fieldInfo.boostPadsLength()) {
            val flatPad = fieldInfo.boostPads(i)
            val ourPad = BoostPad(VectorUtils.convertFromRLVector(flatPad.location()), flatPad.isFullBoost)
            orderedBoosts.add(ourPad)
            if (ourPad.isFullBoost) {
                fullBoosts.add(ourPad)
            } else {
                smallBoosts.add(ourPad)
            }
        }
    }

    fun loadGameTickPacket(packet: GameTickPacket) {
        if (packet.boostPadStatesLength() > orderedBoosts.size) {
            try {
                loadFieldInfo(RLBotDll.getFieldInfo())
            } catch (e: IOException) {
                e.printStackTrace()
                return
            }
        }

        for (i in 0 until packet.boostPadStatesLength()) {
            val boost = packet.boostPadStates(i)
            val existingPad = orderedBoosts[i] // existingPad is also referenced from the fullBoosts and smallBoosts lists
            existingPad.isActive = boost.isActive
        }
    }
}
