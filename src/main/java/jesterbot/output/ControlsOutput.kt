package jesterbot.output

import rlbot.ControllerState

class ControlsOutput(
        var steerVal: Float = 0f,
        var pitchVal: Float = 0f,
        var yawVal: Float = 0f,
        var rollVal: Float = 0f,
        var throttleVal: Float = 0f,
        /** buttons */
        var jumpDepressed: Boolean = false,
        var boostDepressed: Boolean = false,
        var slideDepressed: Boolean = false) : ControllerState
{
    /* 0 is straight, -1 is hard left, 1 is hard right.*/
    fun steer(value: Float) = apply { this.steerVal = clamp(value) }
    /*-1 for front flip, 1 for back flip.*/
    fun pitch(value: Float) = apply { this.pitchVal = clamp(value) }
    /* 0 is straight, -1 is hard left, 1 is hard right.*/
    fun yaw(value: Float) = apply { this.yawVal = clamp(value) }
    /* 0 is straight, -1 is hard left, 1 is hard right.*/
    fun roll(value: Float) = apply { this.rollVal = clamp(value) }
    /* 0 is none, -1 is backwards, 1 is forwards */
    fun throttle(value: Float) = apply { this.throttleVal = clamp(value) }
    fun jump(value: Boolean) = apply { this.jumpDepressed = value }
    fun boost(value: Boolean) = apply { this.boostDepressed = value }
    fun slide(value: Boolean) = apply { this.slideDepressed = value }

    private fun clamp(value: Float) : Float = Math.max(-1f, Math.min(1f, value))

    override fun getSteer(): Float = this.steerVal
    override fun getPitch(): Float = this.pitchVal
    override fun getYaw(): Float = this.yawVal
    override fun getRoll(): Float = this.rollVal
    override fun getThrottle(): Float = this.throttleVal
    override fun holdHandbrake(): Boolean = this.slideDepressed
    override fun holdBoost(): Boolean = this.boostDepressed
    override fun holdJump(): Boolean = this.jumpDepressed
}
