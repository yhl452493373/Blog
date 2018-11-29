package com.yang.blog.entity.base;

import java.time.LocalDateTime;

/**
 * 基类,放置后期不变动的字段.比如记录id,记录创建时间.
 *
 * @param <Entity> 实体对象
 */
@SuppressWarnings("unchecked")
public abstract class BaseEntity<Entity> implements Base<Entity>, Constant, Cloneable {

    /**
     * 记录的id
     */
    private String id;

    /**
     * 记录创建时间
     */
    private LocalDateTime createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
