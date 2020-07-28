package com.emanuel.multithreadedqueueing.producer;

import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;

import java.util.concurrent.BlockingQueue;

public class MessageProducer implements Runnable {

    private final BlockingQueue<SpecifiedMessage> messageQueue;
    private final SpecifiedMessage message;

    public MessageProducer(BlockingQueue<SpecifiedMessage> messageQueue, SpecifiedMessage message) {
        this.messageQueue = messageQueue;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            messageQueue.put(message);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
