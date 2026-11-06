package de.htwg.se.balatro.view

import de.htwg.se.balatro.controller.Controller

import java.io.PrintStream
import java.net.ServerSocket

class SocketServer(controller: Controller, port: Int) {

  def start(): Unit = {
    val thread = new Thread(() => {
      val serverSocket = new ServerSocket(port)
      while true do
        val client = serverSocket.accept()
        val out = new PrintStream(client.getOutputStream, true, "UTF-8")
        TUIRemote(controller, out).update()
    })
    thread.setDaemon(true)
    thread.start()
  }
}