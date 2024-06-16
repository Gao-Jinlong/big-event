package com.ginlon.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.InputStream;

public class AliOssUtil {
  // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
  private static final String endpoint = "https://oss-cn-shanghai.aliyuncs.com";

  // 填写Bucket名称，例如examplebucket。
  private static final String bucketName = "ginlon-big-event";

  // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。

  public static String uploadFile(String objectName, InputStream stream) throws Exception {
    String url = "";

    // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
    EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory
        .newEnvironmentVariableCredentialsProvider();
    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

    try {
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, stream);

      ossClient.putObject(putObjectRequest);

      url = "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + objectName;

    } catch (OSSException oe) {
      System.out.println("Caught an OSSException, which means your request made it to OSS, "
          + "but was rejected with an error response for some reason.");
      System.out.println("Error Message:" + oe.getErrorMessage());
      System.out.println("Error Code:" + oe.getErrorCode());
      System.out.println("Request ID:" + oe.getRequestId());
      System.out.println("Host ID:" + oe.getHostId());
    } catch (ClientException ce) {
      System.out.println("Caught an ClientException, which means the client encountered "
          + "a serious internal problem while trying to communicate with OSS, "
          + "such as not being able to access the network.");
      System.out.println("Error Message:" + ce.getMessage());
    } finally {
      if (ossClient != null) {
        ossClient.shutdown();
      }
    }

    return url;
  }
}