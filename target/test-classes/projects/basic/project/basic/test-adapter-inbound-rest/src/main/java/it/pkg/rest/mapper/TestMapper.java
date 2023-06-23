package it.pkg.rest.mapper;

import it.pkg.rest.config.BaseMapperConfig;
import it.pkg.core.domain.TestCore;
import it.pkg.rest.dto.TestRequest;
import it.pkg.rest.dto.TestResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface TestMapper {

    TestCore testRequestToTestCore(TestRequest testRequest);

    TestResponse testCoreToTestResponse(TestCore test);

    List<TestResponse> testCoreListToTestResponseList(List<TestCore> tests);


}
