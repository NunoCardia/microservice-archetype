package it.pkg.sql.mapper;


import it.pkg.sql.config.BaseMapperConfig;
import it.pkg.sql.entity.TestEntity;
import it.pkg.core.domain.TestCore;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface TestEntityMapper {

    TestCore testEntityToTestCore(TestEntity testEntity);

    TestEntity testCoreToTestEntity(TestCore testCore);

}
