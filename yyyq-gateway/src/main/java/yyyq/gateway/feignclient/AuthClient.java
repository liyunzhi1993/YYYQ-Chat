package yyyq.gateway.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import yyyq.common.constants.SecurityConstants;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.AccountModel;


/**
 * AuthClient
 *
 * @author liyunzhi
 * @date 2018/7/13 17:09
 */
@FeignClient(value = "yyyq-auth")
public interface AuthClient {
    
    @PostMapping(value = "/account/current")
    ActionResultModel<AccountModel> token(@RequestHeader(SecurityConstants.HEADER_STRING) String token);
}
