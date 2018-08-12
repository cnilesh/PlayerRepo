package com.player.main;

import com.player.logic.Initiator;
import com.player.logic.Player;
import com.player.logic.Requestor;
import com.player.pojo.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String...args) {
        BlockingQueue<Message> requestMq = new LinkedBlockingQueue<Message>(1);
        BlockingQueue<Message> responseMq = new LinkedBlockingQueue<Message>(1);
        Player initiator = new Initiator(requestMq, responseMq);
        Player requestor = new Requestor(requestMq, responseMq);
        Thread playerInitiator = new Thread(initiator);
        Thread playerRequestor = new Thread(requestor);
        playerInitiator.start();
        playerRequestor.start();
    }
}
