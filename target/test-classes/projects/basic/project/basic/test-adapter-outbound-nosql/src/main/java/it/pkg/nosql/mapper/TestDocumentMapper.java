package it.pkg.nosql.mapper;

import it.pkg.nosql.config.BaseMapperConfig;
import it.pkg.nosql.document.TestDocument;
import it.pkg.core.domain.TestCore;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface TestDocumentMapper {

    TestCore testDocumentToTestCore(TestDocument testDocument);

    TestDocument testCoreToTestDocument(TestCore testCore);

}
