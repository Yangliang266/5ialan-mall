package com.alan.mall.common.tool.tkmybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: mathyoung
 * @description:
 */
public interface TKMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
