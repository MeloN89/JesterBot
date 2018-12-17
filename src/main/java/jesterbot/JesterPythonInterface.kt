package jesterbot

import jesterbot.bot.DeadBot
import jesterbot.bot.JesterBot
import rlbot.Bot
import rlbot.manager.BotManager
import rlbot.pyinterop.DefaultPythonInterface

class JesterPythonInterface(botManager: BotManager) : DefaultPythonInterface(botManager) {

    override fun initBot(index: Int, botType: String, team: Int): Bot {
        return if (team == 0) {
            JesterBot(index)
        } else {
            DeadBot(index)
        }
    }
}
