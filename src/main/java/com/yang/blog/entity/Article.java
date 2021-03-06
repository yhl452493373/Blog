package com.yang.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yang.blog.entity.base.BaseEntity;

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
public class Article extends BaseEntity<Article> implements Serializable, Cloneable {
    /**
     * 以下两个为是否草稿的常量
     */
    public static final Integer IS_DRAFT_FALSE = 0;//非草稿
    public static final Integer IS_DRAFT_TRUE = 1;//草稿

    private static final long serialVersionUID = 1L;

    /**
     * 博文标题
     */
    private String title;

    /**
     * 博文内容
     */
    private String content;

    /**
     * 博文摘要
     */
    private String summary;

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
     * 博文包含的图片id,多个用英文逗号分隔.主要用于展示时,根据其个数决定用哪个模板.这里只包括图片的
     */
    private String imageFileIds;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 纯文本的内容,用于过滤html标签后生成摘要
     */
    @TableField(exist = false)
    private String planTextContent;

    /**
     * 用户设置的标签列表,逗号分隔,同一用户的标签不可同名
     */
    @TableField(exist = false)
    private String tags;

    /**
     * 与文章对应的文件id，多个用逗号分隔.这里包括任意格式的文件id
     */
    @TableField(exist = false)
    private String fileIds;

    /**
     * 作者名字
     */
    @TableField(exist = false)
    private String authorName;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getImageFileIds() {
        return imageFileIds;
    }

    public void setImageFileIds(String imageFileIds) {
        this.imageFileIds = imageFileIds;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPlanTextContent() {
        return planTextContent;
    }

    public void setPlanTextContent(String planTextContent) {
        this.planTextContent = planTextContent;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title=" + title +
                ", content=" + content +
                ", summary=" + summary +
                ", isDraft=" + isDraft +
                ", readCount=" + readCount +
                ", praiseCount=" + praiseCount +
                ", publishTime=" + publishTime +
                ", modifiedTime=" + modifiedTime +
                ", available=" + available +
                ", imageFileIds=" + imageFileIds +
                ", authorId=" + authorId +
                ", planTextContent=" + planTextContent +
                ", tags=" + tags +
                ", fileIds=" + fileIds +
                ", authorName=" + authorName +
                "}";
    }
}
