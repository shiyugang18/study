package com.syg.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class UploadFile {

    //文件上传路径
    private static String UploadPath;

    public static String getUploadPath() {
        return UploadPath;
    }

    @Value("${upload.file.path}")
    public void setUploadPath(String uploadPath) {
        UploadFile.UploadPath = uploadPath;
    }

    public static String managePath(String dir, String fileName) {
        return UploadFile.getUploadPath() + File.separator + dir + File.separator + fileName;
    }

    public static String resourcePath(String fileName) {
        return managePath("resource", fileName);
    }

    /**
     * 单文件上传
     * @param path
     * @param file
     * @return
     */
    public static String uploadSingleFile(String path, MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        if (!StringUtils.isEmpty(path)) {
            path = UploadPath + File.separator + path;
        } else {
            path = UploadFile.UploadPath;
        }
        String fileName = file.getOriginalFilename();
        if (fileName.length() > 16) {
            fileName = fileName.substring(fileName.length() - 16);
        }
        fileName = UuidMd5.uuidWith22Bit() + fileName;

        try {
            File serverFile = createServerFile(path, fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            int length = 0;
            byte[] buffer = new byte[1024];
            InputStream inputStream = file.getInputStream();
            while ((length = inputStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            stream.flush();
            stream.close();
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 创建文件
     * @param path
     * @param name
     * @return
     */
    private static File createServerFile(String path, String name) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(path + File.separator + name);
        if (file.exists()) {
            file.delete();
        }
        return file;

    }
}
