package com.yang.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yhl452493373.bean.JSONResult;
import com.yang.blog.config.ServiceConfig;
import com.yang.blog.entity.Comment;
import com.yang.blog.shiro.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author User
 * @since 2018-11-20
 */
@RestController
@RequestMapping("/data/comment")
public class CommentController implements BaseController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private ServiceConfig service = ServiceConfig.serviceConfig;

    /**
     * 分页查询数据
     *
     * @param page    分页信息
     * @param comment 查询对象
     * @return 查询结果
     */
    @RequestMapping("/list")
    public JSONResult list(Comment comment, Page<Comment> page) {
        JSONResult jsonResult = JSONResult.init();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(comment);
        queryWrapper.eq("available", Comment.AVAILABLE);
        queryWrapper.isNull("belong_id");
        queryWrapper.orderByDesc("created_time");
        service.commentService.page(page, queryWrapper);
        page.getRecords().forEach(record -> {
            if (StringUtils.isNotEmpty(record.getUserId()))
                record.setUserName(service.userService.findUsernameById(record.getUserId()));
            QueryWrapper<Comment> tempWrapper = new QueryWrapper<>();
            tempWrapper.orderByAsc("created_time");
            tempWrapper.eq("article_id", comment.getArticleId());
            tempWrapper.eq("available", Comment.AVAILABLE);
            tempWrapper.eq("belong_id", record.getId());
            List<Comment> replyList = service.commentService.list(tempWrapper);
            replyList.forEach(reply -> {
                if (StringUtils.isNotEmpty(reply.getUserId()))
                    reply.setUserName(service.userService.findUsernameById(reply.getUserId()));
            });
            record.setReplyList(replyList);
        });
        jsonResult.success().data(page.getRecords()).count(page.getTotal());
        return jsonResult;
    }

    /**
     * 添加数据
     *
     * @param comment 添加对象
     * @return 添加结果，data中包括文章id和评论id，便于跳转
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping("/add")
    public JSONResult add(Comment comment) {
        JSONResult jsonResult = JSONResult.init();
        comment.setAvailable(Comment.AVAILABLE);
        comment.setCreatedTime(LocalDateTime.now());
        if (SecurityUtils.getSubject().isAuthenticated()) {
            comment.setUserName(null);
            comment.setUserId(ShiroUtils.getLoginUser().getId());
        }
        comment.setPraiseCount(0);
        QueryWrapper<Comment> countQueryWrapper = new QueryWrapper<>();
        countQueryWrapper.eq("article_id", comment.getArticleId());
        countQueryWrapper.isNull("belong_id");
        comment.setFloor(service.commentService.count(countQueryWrapper) + 1);
        boolean result = service.commentService.save(comment);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("articleId", comment.getArticleId());
            jsonObject.put("commentId", comment.getId());
            jsonResult.success(ADD_SUCCESS).data(jsonObject);
        } else
            jsonResult.error(ADD_FAILED);
        return jsonResult;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping("/reply")
    public JSONResult reply(Comment comment) {
        QueryWrapper<Comment> countQueryWrapper = new QueryWrapper<>();
        countQueryWrapper.eq("article_id", comment.getArticleId());
        countQueryWrapper.eq("belong_id", comment.getBelongId());
        comment.setBelongFloor(service.commentService.count(countQueryWrapper) + 1);
        JSONResult jsonResult = JSONResult.init();
        comment.setAvailable(Comment.AVAILABLE);
        comment.setCreatedTime(LocalDateTime.now());
        if (SecurityUtils.getSubject().isAuthenticated()) {
            comment.setUserName(null);
            comment.setUserId(ShiroUtils.getLoginUser().getId());
        }
        comment.setPraiseCount(0);
        comment.setFloor(null);
        boolean result = service.commentService.save(comment);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("articleId", comment.getArticleId());
            jsonObject.put("commentId", comment.getId());
            jsonResult.success(ADD_SUCCESS).data(jsonObject);
        } else
            jsonResult.error(ADD_FAILED);
        return jsonResult;
    }

    /**
     * 更新数据
     *
     * @param comment 更新对象
     * @return 添加结果
     */
    @RequestMapping("/update")
    public JSONResult update(Comment comment) {
        JSONResult jsonResult = JSONResult.init();
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        //TODO 根据需要设置需要更新的列，字段值从comment获取。以下注释部分为指定更新列示例，使用时需要注释或删除updateWrapper.setEntity(comment);
        //updateWrapper.set("数据库字段1","字段值");
        //updateWrapper.set("数据库字段2","字段值");
        updateWrapper.eq("表示主键的字段", "comment中表示主键的值");
        boolean result = service.commentService.update(comment, updateWrapper);
        if (result)
            jsonResult.success();
        else
            jsonResult.error();
        return jsonResult;
    }

    /**
     * 删除数据
     *
     * @param comment 删除对象
     * @param logical 是否逻辑删除。默认false，使用物理删除
     * @return 删除结果
     */
    @RequestMapping("/delete")
    public JSONResult delete(Comment comment, @RequestParam(required = false, defaultValue = "true") Boolean logical) {
        JSONResult jsonResult = JSONResult.init();
        boolean result;
        if (logical) {
            UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("available", Comment.DELETE);
            updateWrapper.eq("id", comment.getId());
            result = service.commentService.update(comment, updateWrapper);
        } else {
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
            queryWrapper.setEntity(comment);
            result = service.commentService.remove(queryWrapper);
        }
        if (result)
            jsonResult.success(DELETE_SUCCESS);
        else
            jsonResult.error(DELETE_FAILED);
        return jsonResult;
    }
}