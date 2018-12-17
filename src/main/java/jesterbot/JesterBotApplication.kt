package jesterbot

import jesterbot.util.PortReader
import rlbot.manager.BotManager
import rlbot.pyinterop.PythonServer

/**
 * See JavaAgent.py for usage instructions
 */
object JesterBotApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        val botManager = BotManager()
        val pythonInterface = JesterPythonInterface(botManager)
        val port = PortReader.readPortFromFile("port.cfg")
        val pythonServer = PythonServer(pythonInterface, port)
        pythonServer.start()
    }
}