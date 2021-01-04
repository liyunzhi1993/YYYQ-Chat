package yyyq.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileModel {
    public Long acctid;
    public byte[] imageFile;
    public String fileName;
    public String nickName;
}
