package yyyq.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.chat.entity.UserGame;

import java.util.Map;

@Mapper
public interface UserGameMapper {
    int deleteByPrimaryKey(Long userGameId);

    int insert(UserGame record);

    int insertSelective(UserGame record);

    UserGame selectByPrimaryKey(Long userGameId);

    UserGame selectByUserAndGameId(Map map);

    int updateByPrimaryKeySelective(UserGame record);

    int updateByPrimaryKey(UserGame record);
}