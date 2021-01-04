package yyyq.external.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;
import yyyq.external.service.TencentClientService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class TencentClientServiceImpl implements TencentClientService {

    static final String accessKeyId = "AKIDuhhivzcOFOLJDmhPWbT4CRnsiXGrouF5";
    static final String accessKeySecret = "jFJMDsW0HI3OmsiXA0oGg1A5yg4E8Rh7";
    static final String accessBucket = "yyyq-1255779960";
    static final String regionName = "ap-nanjing";
//    static final String accessUrl = "http://image.yiyouyiqu.com/";

    @Override
    public String uploadImage(String fileName, byte[] fileByte) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = accessBucket;
        // 指定要上传的文件
        InputStream in = new ByteArrayInputStream(fileByte);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        ObjectMetadata metadata = new ObjectMetadata();
        Date expiration = new Date(new Date(System.currentTimeMillis()).getTime() + 3600L * 1000 * 24 * 365 * 100);
        metadata.setContentLength(fileByte.length);
        metadata.setExpirationTime(expiration);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,fileName,in,metadata);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        cosclient.shutdown();
        //通过以下地址可访问到上传的图片
        return "https://" + bucketName + ".cos." + regionName +".myqcloud.com/" + fileName;
    }
}
