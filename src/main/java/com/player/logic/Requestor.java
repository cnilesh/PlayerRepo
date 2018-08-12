package com.player.logic;

import com.player.pojo.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Requestor extends Player implements Runnable {

    private BlockingQueue<Message> requestMq;
    private BlockingQueue<Message> responseMq;
    private AtomicInteger counter = new AtomicInteger(0);
    public Requestor(BlockingQueue<Message> requestMq, BlockingQueue<Message> responseMq) {
        this.requestMq = requestMq;
        this.responseMq = responseMq;
    }

    public void run(){
        for(int i=0; i< 10; i++) {
            Message message = null;
            try {
                message = this.requestMq.take();
            } catch (InterruptedException e) {
                System.err.println("Cannot take message from the queue. Thread Interrupted");
            }
            String receivedMessage = message.getMessage();
            String responseMessage = receivedMessage + "-->" + message.getCounter();
            Message response = new Message();
            response.setMessage(responseMessage);
            response.setType("RESPONSE");
            try {
                this.responseMq.put(response);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted while inserting in message queue");
            }
        }
        }
}
