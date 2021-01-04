package yyyq.external.service;

import java.io.IOException;

public interface TencentClientService {
    /**
     * 上传文件流
     * @param fileName
     * @param fileByte
     * @return
     */
    String uploadImage(String fileName,byte[] fileByte);
}
