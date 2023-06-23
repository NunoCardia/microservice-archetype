package it.pkg.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class EventInfo {

    @JsonProperty(value = "eventId")
    private String eventId;

    @JsonProperty(value = "eventAction")
    private ActionModel eventActionModel;

    @JsonProperty(value = "eventSourceSystem")
    private String eventSourceSystem;

    @JsonProperty(value = "eventEntityName")
    private String eventEntityName;
}
