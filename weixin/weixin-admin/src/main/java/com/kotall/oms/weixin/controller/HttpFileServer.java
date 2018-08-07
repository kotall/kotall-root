package com.kotall.oms.weixin.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author: zpwang
 * @version: 1.0.0
 * @date: 2018/5/7
 */
@Slf4j
@Controller
@RequestMapping("/wx/file")
public class HttpFileServer {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public void upload() {
        log.info("====== upload =====");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        File filePath = new File("D:/tmp");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }


        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (isMultipartContent) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(1 * 1024 * 1024);
                factory.setRepository(filePath);

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(50 * 1024 * 1024);
                upload.setSizeMax(50 * 1024 * 1024);
                upload.setHeaderEncoding("utf-8");

                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

                Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

                if (null != fileMap) {
                    fileMap.forEach((name, file) -> {
                        if (!file.isEmpty()) {
                            String fieldName = file.getName();
                            name = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("/") + 1);
                            log.info("================ fieldName = {}", fieldName);
                            log.info("================ fileName  = {}", name);
                            InputStream is = null;
                            FileOutputStream fos = null;
                            try {
                                is = file.getInputStream();
                                fos = new FileOutputStream(new File(filePath, name));
                                int temp;
                                while ((temp = is.read()) > 0) {
                                    fos.write(temp);
                                }

                            } catch (IOException e) {
                                log.error("error:", e);
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (fos != null) {
                                        fos.close();
                                    }
                                } catch (Exception e) {

                                }

                            }
                        }
                    });
                }

            }
            response.getOutputStream().write("Ok".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            String userName = request.getHeader("userName");
            String passwd = request.getHeader("password");
            String fileName = request.getHeader("fileName");

            log.info("userName={}, password={}, fileName={}", userName, passwd, fileName);
            File file = new File("D:/tmp/" + fileName);
            response.setContentLength((int) file.length());
            response.setHeader("Accept-Ranges", "bytes");

            log.info("==========download file name: {}", file.getAbsolutePath());
            is = new BufferedInputStream(new FileInputStream(file), 1024);
            os = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[1024];
            int temp;
            while ((temp = is.read()) > 0) {
                byte[] bytes = new byte[temp];
                System.arraycopy(buffer, 0, bytes, 0, temp);
                os.write(bytes);
            }
            os.flush();

        } catch (Exception e) {

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                log.error("", e);
            }

        }
    }
}
