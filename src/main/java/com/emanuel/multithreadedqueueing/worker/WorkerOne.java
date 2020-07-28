package com.emanuel.multithreadedqueueing.worker;

import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;

import java.util.concurrent.BlockingQueue;

public class WorkerOne implements Runnable {

    private final BlockingQueue<SpecifiedMessage> specifiedMessageQueue;

    public WorkerOne(BlockingQueue<SpecifiedMessage> messageBlockingQueue) {
        this.specifiedMessageQueue = messageBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SpecifiedMessage message = specifiedMessageQueue.take();
                handleMessage(message);
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleMessage(SpecifiedMessage message) {
        // do something interesting with the message in accordance with WorkerOne.
    }
}
