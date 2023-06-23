package it.pkg.kafka.consumer;

import it.pkg.core.adapter.TestEventServiceAdapter;
import it.pkg.core.port.in.TestEventService;
import it.pkg.kafka.mapper.TestCoreKafkaMapper;
import com.once.triangleproject.kafka.model.EventInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class TestEventConsumer {

    private final TestEventService service;
    private final TestCoreKafkaMapper coreMapper;

    public TestEventConsumer(TestEventServiceAdapter service, TestCoreKafkaMapper coreMapper) {
        this.service = service;
        this.coreMapper = coreMapper;
    }

    @Bean
    public Consumer<Message<EventInfo>> testEventConsumer() {
        return eventInfoMessage -> log.info("Got {} from kafka", eventInfoMessage.getPayload());
    }
}
