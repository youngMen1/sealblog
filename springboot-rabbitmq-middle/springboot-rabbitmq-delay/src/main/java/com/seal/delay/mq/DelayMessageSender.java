package com.seal.delay.mq;

import com.seal.delay.constants.DelayTypeEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.seal.delay.config.DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME;
import static com.seal.delay.config.DelayedRabbitMQConfig.DELAYED_ROUTING_KEY;
import static com.seal.delay.config.RabbitMQConfig.*;

@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type) {
        switch (type) {
            case DELAY_10s:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }

    public void sendMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, msg, a -> {
            a.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return a;
        });
    }

    public void sendDelayMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, msg, a -> {
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }
}
