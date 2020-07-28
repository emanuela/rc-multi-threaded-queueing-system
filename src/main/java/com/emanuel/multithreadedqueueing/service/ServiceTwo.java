package com.emanuel.multithreadedqueueing.service;

import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;
import com.emanuel.multithreadedqueueing.domain.SpecifiedMessageType;
import com.emanuel.multithreadedqueueing.producer.MessageProducer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ServiceTwo {

    private final List<BlockingQueue<SpecifiedMessage>> messageQueues;

    public ServiceTwo(List<BlockingQueue<SpecifiedMessage>> messageQueues) {
        this.messageQueues = messageQueues;
    }

    public void sendSpecifiedMessage(SpecifiedMessage specifiedMessage) {

        SpecifiedMessageType messageType = specifiedMessage.getMessageType();
        switch (messageType) {
            case ONE: sendMessage(messageQueues.get(0), specifiedMessage);
            case TWO: sendMessage(messageQueues.get(1), specifiedMessage);
            case THREE: sendMessage(messageQueues.get(2), specifiedMessage);
            case FOUR: sendMessage(messageQueues.get(3), specifiedMessage);
        }
    }

    private void sendMessage(BlockingQueue messageQueue, SpecifiedMessage specifiedMessage) {
        new Thread(new MessageProducer(messageQueue, specifiedMessage)).start();
    }
}
