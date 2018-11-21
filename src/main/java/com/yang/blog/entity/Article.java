package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 博文内容
 * </p>
 *
 * @author User
 * @since 2018-11-20
 */
public class Article implements Serializable, BaseEntity {
    public static final Integer IS_DRAFT_FALSE = 0;//非草稿
    public static final Integer IS_DRAFT_TRUE = 1;//草稿

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 博文所属用户id
     */
    private String userId;

    /**
     * 博文标题
     */
    private String title;

    /**
     * 博文内容
     */
    private String content;

    /**
     * 是否是草稿。单个用户最多只会有一个草稿。0-否，1-是
     */
    private Integer isDraft;

    /**
     * 阅读次数
     */
    private Integer readCount;

    /**
     * 点赞次数
     */
    private Integer praiseCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 博文状态。-1-删除，0-不可见，1-正常
     */
    private Integer available;

    /**
     * 用户设置的标签列表,逗号分隔,同一用户的标签不可同名
     */
    @TableField(exist = false)
    private String tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Integer isDraft) {
        this.isDraft = isDraft;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title=" + title +
                ", content=" + content +
                ", isDraft=" + isDraft +
                ", readCount=" + readCount +
                ", praiseCount=" + praiseCount +
                ", createdTime=" + createdTime +
                ", publishTime=" + publishTime +
                ", modifiedTime=" + modifiedTime +
                ", available=" + available +
                ", tags=" + tags +
                "}";
    }
}
