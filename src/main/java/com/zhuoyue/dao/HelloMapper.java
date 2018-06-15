package com.zhuoyue.dao;


import com.zhuoyue.bean.Hello;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 14258
 */
@Mapper
public interface HelloMapper {

    void insert(Hello hello);

}