package ljs.spring.mapper.test;

import ljs.spring.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapperTest {
    @Insert("insert into user(id, name,age) values(#{id},#{name},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(User user);
}
