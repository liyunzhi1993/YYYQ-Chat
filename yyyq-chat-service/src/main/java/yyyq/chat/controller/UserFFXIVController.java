package yyyq.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yyyq.common.exception.CustomException;
import yyyq.chat.entity.UserFFXIV;
import yyyq.chat.model.select.UserFFXIVSelectListModel;
import yyyq.chat.service.UserFFXIVService;
import yyyq.common.controller.BaseController;
import yyyq.common.model.ActionResultModel;


/**
 * UserController
 *
 * @author liyunzhi
 * @date 2018/7/5 11:12
 */
@RestController
@RequestMapping("/userffxiv")
public class UserFFXIVController extends BaseController {

    @Autowired
    private UserFFXIVService userFFXIVService;

    @PostMapping("/get")
    public ActionResultModel<UserFFXIV> getUserFFXIV() {
        ActionResultModel<UserFFXIV> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userFFXIVService.getUserFFXIV(accountModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
    
    @PostMapping("/save")
    public ActionResultModel<String> login(@RequestBody UserFFXIV userFFXIV) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            userFFXIVService.saveUserFFXIV(userFFXIV, accountModel);
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/getSelectList")
    public ActionResultModel<UserFFXIVSelectListModel> getUserFFXIVSelectList() {
        ActionResultModel<UserFFXIVSelectListModel> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userFFXIVService.getUserFFXIVSelectList());
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
