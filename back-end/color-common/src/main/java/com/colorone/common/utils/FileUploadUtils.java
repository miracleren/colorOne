package com.colorone.common.utils;

import com.colorone.common.utils.data.DateUtils;
import com.colorone.common.utils.data.IdUtils;
import com.colorone.common.utils.data.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/26
 * @备注：文件上传下载管理工具
 */
@Component
public class FileUploadUtils {
    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 获取配置文件池路径
     */
    private static String filePool;

    @Value("${colorOne.filePool}")
    public void setFilePool(String filePool) {
        FileUploadUtils.filePool = filePool;
    }

    public static final String[] ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf"};


    /**
     * 文件上传
     *
     * @param fileDir
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file, String fileDir) throws Exception {
        if (StringUtils.isEmpty(fileDir))
            fileDir = filePool;

        String fileName = file.getOriginalFilename();
        int fileNameLength = Objects.requireNonNull(fileName).length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new Exception("文件名长度超" + FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        checkAllowedFileType(fileName);

        String savePath = File.separator + DateUtils.getStringDay(DateUtils.YYYY_MM, 0) + File.separator;
        savePath += IdUtils.randomNoSignUUID() + "-" + fileName;
        File desc = getAbsoluteFile(fileDir + savePath);
        file.transferTo(desc);
        return savePath;
    }

    public static void download(String fileName, String filePath, HttpServletResponse response) throws Exception {
        checkAllowedFileType(fileName);

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String encodeName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(encodeName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(encodeName);

        response.setHeader("Content-disposition", contentDispositionValue.toString());

        writeBytes(filePool + filePath, response.getOutputStream());
    }

    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            os.close();
            if (fis != null)
                fis.close();
        }
    }

    /**
     * 检查文件类型
     *
     * @param fileName
     * @throws Exception
     */
    public static void checkAllowedFileType(String fileName) throws Exception {
        if (!Arrays.asList(ALLOWED_EXTENSION).contains(getExtension(fileName))) {
            throw new Exception("服务器不允许上传下载当前格式文件");
        }
    }

    /**
     * 目录
     *
     * @param savePath
     * @return
     * @throws IOException
     */
    private static File getAbsoluteFile(String savePath) throws IOException {
        File desc = new File(savePath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 获取文件名的后缀
     *
     * @param fileName 表单文件
     * @return 后缀名
     */
    public static String getExtension(String fileName) {
        if (fileName != null) {
            int lastIndex = fileName.lastIndexOf(".");
            if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
                return fileName.substring(lastIndex + 1);
            }
        }
        return "";
    }

    /**
     * 返回filePool值
     *
     * @return
     */
    private String getFilePool() {
        return filePool;
    }
}
