#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.kafka.consumer;

import ${package}.core.adapter.${entity}EventServiceAdapter;
import ${package}.core.port.in.${entity}EventService;
import ${package}.kafka.mapper.${entity}CoreKafkaMapper;
import com.once.triangleproject.kafka.model.EventInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class ${entity}EventConsumer {

    private final ${entity}EventService service;
    private final ${entity}CoreKafkaMapper coreMapper;

    public ${entity}EventConsumer(${entity}EventServiceAdapter service, ${entity}CoreKafkaMapper coreMapper) {
        this.service = service;
        this.coreMapper = coreMapper;
    }

    @Bean
    public Consumer<Message<EventInfo>> ${entity.toLowerCase()}EventConsumer() {
        return eventInfoMessage -> log.info("Got {} from kafka", eventInfoMessage.getPayload());
    }
}
