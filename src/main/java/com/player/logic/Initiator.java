package com.player.logic;

import com.player.pojo.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Initiator extends Player implements Runnable {
    private BlockingQueue<Message> requestMq;
    private BlockingQueue<Message> responseMq;
    private AtomicInteger counter = new AtomicInteger(0);
    public Initiator(BlockingQueue<Message> requestMq, BlockingQueue<Message> responseMq) {
        this.requestMq = requestMq;
        this.responseMq = responseMq;
    }

    public void run(){
        for(int i = 0; i < 10; i++) {
            Message requestMessage = new Message();
            requestMessage.setType("REQUEST");
            requestMessage.setCounter(this.counter.incrementAndGet());
            requestMessage.setMessage("This is a dummy message");
            try {
                this.requestMq.put(requestMessage);
                System.out.println("REQUEST SENT WITH MESSAGE:-->" + requestMessage.getMessage());
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted while inserting in message queue");
            }
                try {
                    Message resp = this.responseMq.take();
                    System.out.println("RESPONSE" + resp.getMessage());
                    if (resp.getMessage().contains("10")) {
                        Thread.currentThread().interrupt();
                    }
                } catch (InterruptedException e) {
                    System.err.println("Cannot take message from the queue. Thread Interrupted");
                }
        }
    }
}
