package com.colorone.system.controller;

import com.colorone.common.domain.core.RequestResult;
import com.colorone.common.frame.aspect.annotation.ApiExtension;
import com.colorone.common.frame.aspect.enums.PermitType;
import com.colorone.common.utils.FileUploadUtils;
import com.colorone.common.utils.data.StringUtils;
import com.colorone.system.domain.entity.BaseFilePool;
import com.colorone.system.service.BaseFilePoolService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/10/20
 * @备注：通用文件管理接口
 */
@RestController
@RequestMapping("/pool/")
public class BaseFilePoolController {
    @Autowired
    private BaseFilePoolService filePoolService;

    /**
     * 查询通用文件列表
     *
     * @param refName
     * @param refId
     * @return
     */
    @GetMapping("/file/list")
    @ApiExtension(name = "查询通用文件列表", permitType = PermitType.ROLE)
    public RequestResult getBaseFilePoolList(String refName, String refId) {
        return RequestResult.success(filePoolService.getBaseFilePoolList(refName, refId));
    }

    /**
     * 通用文件下载接口
     *
     * @param fileId
     * @return
     */
    @ApiExtension(name = "通用文件下载接口", permitType = PermitType.PATH_TOKEN)
    @GetMapping("/file/download/{fileId}")
    public void downloadFile(@PathVariable String fileId, HttpServletResponse response) throws Exception {
        try {
            BaseFilePool file = filePoolService.getBaseFilePoolById(fileId);
            String realFileName = file.getFileName();
            String filePath = file.getSavePath();
            //下载文件
            FileUploadUtils.download(realFileName, filePath, response);
        } catch (Exception ignored) {
            throw new Exception("文件下载异常");
        }
    }


    /**
     * 通用文件上传接口
     *
     * @param file
     * @param refName
     * @param refId
     * @return
     */
    @ApiExtension(name = "通用文件上传接口", permitType = PermitType.LOGIN)
    @PostMapping("/file/upload")
    public RequestResult uploadFile(MultipartFile file, BaseFilePool filePool) {
        try {
            // 上传并返回完整文件路径
            String savePath = FileUploadUtils.upload(file, null);
            if (StringUtils.isNotEmpty(savePath)) {
                //上传成功记录文件到数据库
                filePool.setFileName(file.getOriginalFilename());
                filePool.setSavePath(savePath);
                int res = filePoolService.addBaseFilePool(filePool);
                if (res > 0)
                    return RequestResult.success(filePool.getFileId());
            }

        } catch (Exception e) {
            return RequestResult.error(e.getMessage());
        }
        return RequestResult.error("文件上传失败！");
    }

    /**
     * 通用文件删除删除接口
     *
     * @param fileId 文件键值
     * @return 成功失败
     */
    @DeleteMapping("/file/delete/{fileId}")
    @ApiExtension(name = "删除文件数据", permitType = PermitType.ROLE)
    public RequestResult deleteBaseFilePool(@PathVariable String fileId) {
        return RequestResult.success(filePoolService.deleteBaseFilePool(fileId) > 0);
    }

}