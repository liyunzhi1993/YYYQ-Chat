package yyyq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import yyyq.auth.entity.Account;
import yyyq.auth.model.AddFriendModel;
import yyyq.auth.model.EditProfileModel;
import yyyq.auth.model.LoginModel;
import yyyq.auth.model.RegisterModel;
import yyyq.auth.security.JwtTokenProvider;
import yyyq.auth.service.AccountService;
import yyyq.common.annotation.Auth;
import yyyq.common.controller.BaseController;
import yyyq.common.exception.CustomException;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.AccountModel;

import java.io.IOException;

/**
 * UserController
 *
 * @author liyunzhi
 * @date 2018/7/5 11:12
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ActionResultModel<String> register(@RequestBody RegisterModel registerModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(accountService.register(registerModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/login")
    public ActionResultModel<String> login(@RequestBody LoginModel loginModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(accountService.login(loginModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/addFriend")
        public ActionResultModel<String> addFriend(@RequestHeader Long acctID,@RequestParam String friendID) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            AddFriendModel addFriendModel=new AddFriendModel();
            addFriendModel.acctID=acctID;
            addFriendModel.friendID=friendID;
            actionResultModel.setActionResult(accountService.addFriend(addFriendModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("editProfile")
    public ActionResultModel<String> editProfile(@RequestHeader Long acctID,@RequestBody EditProfileModel editProfileModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            editProfileModel.acctid = acctID;
            actionResultModel.setActionResult(accountService.editProfile(editProfileModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("editAvatar")
    public ActionResultModel<String> editProfile(@RequestHeader Long acctID,@RequestParam("file") MultipartFile file) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            EditProfileModel editProfileModel=new EditProfileModel();
            if(!file.isEmpty()) {
                editProfileModel.fileName = file.getOriginalFilename();
                editProfileModel.imageFile = file.getBytes();
            }
            editProfileModel.acctid = acctID;
            actionResultModel.setActionResult(accountService.editProfile(editProfileModel));
        } catch (CustomException | IOException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("current")
    public ActionResultModel<AccountModel> current() {
        ActionResultModel<AccountModel> actionResultModel = new ActionResultModel<>();
        try {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AccountModel accountModel=new AccountModel();
            accountModel.acctId=account.acctid;
            accountModel.nickName=account.nickname;
            accountModel.avatar=account.portrait;
            accountModel.userName=account.mobile;
            actionResultModel.setActionResult(accountModel);
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
