package com.cm.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author cm.g
 * @date 2020/9/11 14:15
 * @Description:
 */
@Service
@Slf4j
public class BookProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object o) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, o);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                log.info("生产者成功发送消息到" + topic + "-> " + sendResult.getProducerRecord().value().toString());
            }
            @Override
            public void onFailure(Throwable throwable) {
                log.error("生产者发送消息：{} 失败，原因：{}", o.toString(), throwable.getMessage());
            }
        });
        /***********************/
//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, o);
//        future.addCallback(result -> log.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
//                ex -> log.error("生产者发送消失败，原因：{}", ex.getMessage()));
    }
}
