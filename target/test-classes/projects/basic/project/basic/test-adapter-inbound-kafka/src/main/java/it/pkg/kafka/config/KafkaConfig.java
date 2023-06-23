package it.pkg.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSchemaRegistryClient
public class KafkaConfig {

}
