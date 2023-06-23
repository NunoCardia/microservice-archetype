package it.pkg.nosql.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDocument{
    @Id
    private String id;
}
