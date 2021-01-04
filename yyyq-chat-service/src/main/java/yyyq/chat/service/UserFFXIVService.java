package yyyq.chat.service;

import yyyq.common.model.auth.AccountModel;
import yyyq.chat.entity.UserFFXIV;
import yyyq.chat.model.select.UserFFXIVSelectListModel;

/**
 * UserService
 *
 * @author liyunzhi
 * @date 2018/7/12 19:45
 */
public interface UserFFXIVService {

    /**
     * 获取用户的游戏信息
     * @return
     */
    UserFFXIV getUserFFXIV(AccountModel accountModel);

    /**
     * 保存用户的游戏信息
     * @param userFFXIV
     * @return
     */
    boolean saveUserFFXIV(UserFFXIV userFFXIV, AccountModel accountModel);

    /**
     * 获取所有下拉绑定列表
     * @return
     */
    UserFFXIVSelectListModel getUserFFXIVSelectList();
}
