package com.emanuel.multithreadedqueueing.config;

import com.emanuel.multithreadedqueueing.consumer.SpecifiedMessageConsumer;
import com.emanuel.multithreadedqueueing.domain.SpecifiedMessage;
import com.emanuel.multithreadedqueueing.service.ServiceOne;
import com.emanuel.multithreadedqueueing.service.ServiceTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class DemoConfig {

    private List<BlockingQueue<SpecifiedMessage>> incomingMessageQueue =
            new ArrayList<BlockingQueue<SpecifiedMessage>>();
    {
        incomingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        incomingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        incomingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        incomingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
    };

    private List<BlockingQueue<SpecifiedMessage>> outGoingMessageQueue =
            new ArrayList<BlockingQueue<SpecifiedMessage>>();
    {
        outGoingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        outGoingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        outGoingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());
        outGoingMessageQueue.add(new LinkedBlockingQueue<SpecifiedMessage>());

    };


    @Bean
    public void config() {

        ServiceOne serviceOne = new ServiceOne(incomingMessageQueue);
        ServiceTwo serviceTwo = new ServiceTwo(incomingMessageQueue);

        new Thread(new SpecifiedMessageConsumer(incomingMessageQueue, outGoingMessageQueue)).start();
    }
}
