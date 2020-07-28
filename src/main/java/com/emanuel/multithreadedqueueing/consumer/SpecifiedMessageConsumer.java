package com.emanuel.multithreadedqueueing.consumer;

import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;
import com.emanuel.multithreadedqueueing.worker.WorkerOne;
import com.emanuel.multithreadedqueueing.worker.WorkerTwo;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SpecifiedMessageConsumer implements Runnable {

    private final static int MAX_QUEUE_SIZE = 4;

    private final List<BlockingQueue<SpecifiedMessage>> incomingMessageQueue;
    private final List<BlockingQueue<SpecifiedMessage>> outGoingMessageQueue;

    private int queueIndex;

    public SpecifiedMessageConsumer(List<BlockingQueue<SpecifiedMessage>> incomingMessageQueue,
                                    List<BlockingQueue<SpecifiedMessage>> outGoingMessageQueue) {
        this.incomingMessageQueue = incomingMessageQueue;
        this.outGoingMessageQueue = outGoingMessageQueue;
    }

    @Override
    public void run() {
            while (true) {
                SpecifiedMessage specifiedMessage = incomingMessageQueue.get(queueIndex).peek();
                if (specifiedMessage != null) {
                    switch (queueIndex) {
                        case 0:
                            new Thread(new WorkerOne(outGoingMessageQueue.get(0))).start();
                        case 1:
                            new Thread(new WorkerTwo(outGoingMessageQueue.get(1))).start();
                        case 2:
                            new Thread(new WorkerTwo(outGoingMessageQueue.get(2))).start();
                        case 3:
                            new Thread(new WorkerOne(outGoingMessageQueue.get(3))).start();
                    }
                    queueIndex = getNextQueueIndex(queueIndex);
                }
            }
    }

    private int getNextQueueIndex(int currentIndex) {
        int nextQueueIndex = currentIndex++;
        if (nextQueueIndex == MAX_QUEUE_SIZE) {
            nextQueueIndex = 0;
        }
        return nextQueueIndex;
    }
}