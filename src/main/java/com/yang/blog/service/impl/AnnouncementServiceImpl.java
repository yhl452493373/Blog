package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Announcement;
import com.yang.blog.mapper.AnnouncementMapper;
import com.yang.blog.service.AnnouncementService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告信息 服务实现类
 * </p>
 *
 * @author User
 * @since 2018-11-27
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Override
    public void setOtherAvailable(Integer available) {
        baseMapper.setOtherAvailable(available);
    }

    @Override
    public Announcement getNewest() {
        return baseMapper.getNewest();
    }
}
