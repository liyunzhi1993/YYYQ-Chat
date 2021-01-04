package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.Friend;
import yyyq.auth.entity.FriendKey;

@Mapper
public interface FriendMapper {
    int deleteByPrimaryKey(FriendKey key);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(FriendKey key);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}