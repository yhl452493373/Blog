package com.yang.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.multipart.MultipartFile;

/**
 * 大文件分片入参实体
 */
public class MultipartFileParam {
    /**
     * 是否预先上传文信息的CHUNK值。如果CHUNK是这个值，这说明是用来比较文件大小等上传前的判断操作的
     */
    public static Integer PRE_UPLOAD_CHUNK = -1;

    /**
     * 文件传输任务ID,由于用的uuid,因此可以用于临时文件名
     */
    private String taskId;

    /**
     * 是否分片上传，默认否
     */
    private boolean isChunk = false;

    /**
     * 当前为第几分片
     */
    private int chunk;

    /**
     * 每个分片的大小
     */
    private long chunkSize;

    /**
     * 分片总数
     */
    private int chunkTotal;

    /**
     * 上传文件的文件名
     */
    private String fileName;

    /**
     * 上传文件的文件类型
     */
    private String fileType;

    /**
     * 上传文件的文件大小
     */
    private long fileSize;

    /**
     * 分片文件传输对象.如果不分片则为整个文件对象
     */
    @JSONField(serialize = false)
    private MultipartFile file;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean getIsChunk() {
        return isChunk;
    }

    public void setIsChunk(boolean isChunk) {
        this.isChunk = isChunk;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public int getChunkTotal() {
        return chunkTotal;
    }

    public void setChunkTotal(int chunkTotal) {
        this.chunkTotal = chunkTotal;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
