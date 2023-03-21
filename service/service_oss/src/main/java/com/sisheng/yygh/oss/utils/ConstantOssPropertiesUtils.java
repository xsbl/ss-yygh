package com.sisheng.yygh.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author bobochang
 * @description
 * @created 2022/7/12-10:25 PM
 **/
@Component
public class ConstantOssPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String region;

    @Value("${aliyun.oss.file.keyid}")
    private String secretId;

    @Value("${aliyun.oss.file.keysecret}")
    private String secretKey;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String REGION;
    public static String SECRETID;
    public static String SECRECTKEY;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION = region;
        SECRETID = secretId;
        SECRECTKEY = secretKey;
        BUCKETNAME = bucketName;
    }
}
