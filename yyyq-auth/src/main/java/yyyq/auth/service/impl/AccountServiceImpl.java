package yyyq.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yyyq.auth.entity.Account;
import yyyq.auth.entity.Friend;
import yyyq.auth.mapper.AccountMapper;
import yyyq.auth.mapper.FriendMapper;
import yyyq.auth.model.AddFriendModel;
import yyyq.auth.model.EditProfileModel;
import yyyq.auth.model.LoginModel;
import yyyq.auth.model.RegisterModel;
import yyyq.auth.security.JwtTokenProvider;
import yyyq.auth.service.AccountService;
import yyyq.common.exception.CustomException;
import yyyq.common.util.StringUtil;
import yyyq.external.service.TencentClientService;

import java.util.Random;
import java.util.UUID;

/**
 * UserServiceImpl
 *
 * @author liyunzhi
 * @date 2018/7/10 15:22
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private TencentClientService tencentClientService;

    private static final String redisPrefix="YYYQ-AUTH";


    private static final String [] imageType={".jpg",".png"};

    private static final String imagePrefix="avatar_";


    @Value("${yyyq.default.avatar.url}")
    private String defaultAvatarUrl;

    @Value("${yyyq.default.banner.url}")
    private String defaultBannerUrl;


    /**
     * 登录
     *
     * @param loginModel
     * @return
     */
    @Override
    public String login(LoginModel loginModel){
        if (StringUtil.isNullOrWhiteSpace(loginModel.userName)) {
            throw new CustomException("用户名不能为空");
        }
        if (StringUtil.isNullOrWhiteSpace(loginModel.passWord)) {
            throw new CustomException("密码不能为空");
        }
        Account account = accountMapper.selectByMobile(loginModel.userName);
        if (account == null) {
            throw new CustomException("用户不存在");
        } else {
            if (bCryptPasswordEncoder.matches(loginModel.passWord,account.acctpwd)) {
                //验证有效期为1天，如果记住我，则是30天
                long validityInMilliseconds=86400000;
                //缓存登录数据到redis
                return jwtTokenProvider.createToken(account.acctid.toString(), account.mobile,validityInMilliseconds);
            } else {
                throw new CustomException("用户名/密码错误");
            }
        }
    }

    /**
     * 注册
     *
     * @param registerModel
     * @return
     */
    @Override
    public String register(RegisterModel registerModel) {
        Account account=new Account();
        account.accttype=0;
        account.mobile=registerModel.userName;
        account.acctpwd=bCryptPasswordEncoder.encode(registerModel.passWord);
        //设置默认
        account.nickname = registerModel.nickName;
        account.status=0;
        account.portrait=defaultAvatarUrl;
        if (accountMapper.insert(account) > 0) {
            Account registerAccount = accountMapper.selectByMobile(registerModel.userName);
            long validityInMilliseconds=86400000;
            return jwtTokenProvider.createToken(registerAccount.acctid.toString(), registerAccount.mobile,validityInMilliseconds);
        } else {
            throw new CustomException("注册异常，请稍后重试");
        }
    }

    /**
     * 编辑用户信息
     *
     * @param editProfileModel
     * @return
     */
    @Override
    public String editProfile(EditProfileModel editProfileModel) {
        Account account=accountMapper.selectByPrimaryKey(editProfileModel.acctid);
        if (!StringUtil.isNullOrWhiteSpace(editProfileModel.nickName))
        {
            account.nickname=editProfileModel.nickName;
                accountMapper.updateByPrimaryKey(account);
        }
        if(editProfileModel.imageFile!=null)
        {
            String avatarUrl= tencentClientService.uploadImage(UUID.randomUUID().toString()+editProfileModel.fileName.replace("?",""),editProfileModel.imageFile);
            account.portrait=avatarUrl;
            accountMapper.updateByPrimaryKeySelective(account);
            return avatarUrl;
        }
        return "";
    }

    @Override
    public String addFriend(AddFriendModel addFriendModel) {
        Account account=accountMapper.selectByMobile(addFriendModel.friendID);
        if(account==null)
        {
            return "找不到该用户";
        }else{
            Friend friend=new Friend();
            friend.acctid=addFriendModel.acctID;
            friend.friendid=account.acctid;
            friend.status=0;
            friendMapper.insert(friend);
            return "添加成功";
        }
    }
}
