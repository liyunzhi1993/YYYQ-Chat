package yyyq.auth.service;

import yyyq.auth.model.AddFriendModel;
import yyyq.auth.model.EditProfileModel;
import yyyq.auth.model.LoginModel;
import yyyq.auth.model.RegisterModel;

/**
 * UserService
 *
 * @author liyunzhi
 * @date 2018/7/12 19:45
 */
public interface AccountService {

    /**
     * 登录
     * @param loginModel
     * @return
     */
    String login(LoginModel loginModel);

    /**
     * 注册
     * @param registerModel
     * @return
     */
    String register(RegisterModel registerModel);

    /**
     * 编辑用户信息
     * @param editProfileModel
     * @return
     */
    String editProfile(EditProfileModel editProfileModel);

    String addFriend(AddFriendModel addFriendModel);
}
