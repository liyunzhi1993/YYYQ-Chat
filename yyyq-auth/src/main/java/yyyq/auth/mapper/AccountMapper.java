package yyyq.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import yyyq.auth.entity.Account;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Long acctid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long acctid);

    Account selectByMobile(String mobile);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}