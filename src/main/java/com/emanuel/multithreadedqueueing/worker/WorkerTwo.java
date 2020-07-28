package com.emanuel.multithreadedqueueing.worker;

import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;

import java.util.concurrent.BlockingQueue;

public class WorkerTwo implements Runnable {

    private final BlockingQueue<SpecifiedMessage> specifiedMessageQueue;

    public WorkerTwo(BlockingQueue<SpecifiedMessage> specifiedMessageQueue) {
        this.specifiedMessageQueue = specifiedMessageQueue;
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
        // do something interesting with the message in accordance with WorkerTwo.
    }
}
