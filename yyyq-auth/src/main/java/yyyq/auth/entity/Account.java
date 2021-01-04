package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Account{
    public Long acctid;

    public String portrait;

    public Byte accttype;

    public String nickname;

    public String telprefix;

    public String mobile;

    public String email;

    public String acctcode;

    public Byte rank;

    public Integer rankvalue;

    public Date rankvaliduntil;

    public Byte vipgrade;

    public String acctpwd;

    public String txpwd;

    public String channel;

    public Long referralacctid;

    public Long parentacctid;

    public Integer level;

    public Byte credit;

    public Date registerdate;

    public Short status;

    public Boolean realnameapproved;
}