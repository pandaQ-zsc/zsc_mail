package com.zsc.hahamall.member.dao;

import com.zsc.hahamall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zsc_xq
 * @email zsc_xq@gmail.com
 * @date 2021-05-20 10:00:17
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
