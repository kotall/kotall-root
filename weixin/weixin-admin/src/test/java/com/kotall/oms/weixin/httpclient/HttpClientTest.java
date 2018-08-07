package com.kotall.oms.weixin.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/7
 */
@Slf4j
public class HttpClientTest {

    @Test
    public void testUploadMultiForm() throws Exception {
        File file = new File("D:/note.txt");
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost/wxadmin/file/upload");

        FileBody fileBody = new FileBody(file);
        StringBody userName = new StringBody("Scott", ContentType.create("text/plain", Consts.UTF_8));
        StringBody password = new StringBody("123456", ContentType.create("text/plain", Consts.UTF_8));

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("media", fileBody)
                .addPart("userName", userName)
                .addPart("password", password)
                .build();

        httpPost.setEntity(reqEntity);

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        if (null != httpEntity) {
            log.info("Response content length: {}", httpEntity.getContentLength());
            log.info(EntityUtils.toString(httpEntity, Charset.forName("UTF-8")));

            EntityUtils.consume(httpEntity);
        }
    }

    @Test
    public void testDownload() throws Exception {
        HttpGet httpGet = new HttpGet("http://localhost/wxadmin/file/download");
        httpGet.addHeader("userName", "Scott");
        httpGet.addHeader("password", "123456");
        httpGet.addHeader("fileName", "note.txt");

        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = httpResponse.getEntity();
        long length = httpEntity.getContentLength();
        if (length <= 0) {
            log.info("文件不存在！");
            return;
        }
        InputStream is = null;
        OutputStream os = null;

        is = httpEntity.getContent();

        File localFile = new File("D:/tmp/tmp01.txt");
        if (!localFile.exists()) {
            localFile.createNewFile();
        }

        os = new FileOutputStream(localFile);
        byte[] buffer = new byte[4096];
        int readLength = 0;
        while ((readLength = is.read(buffer)) > 0) {
            byte[] bytes = new byte[readLength];
            System.arraycopy(buffer, 0, bytes, 0, readLength);
            os.write(bytes);
        }

        os.flush();

        is.close();
        os.close();
    }
}
