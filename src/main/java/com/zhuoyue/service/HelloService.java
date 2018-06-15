package com.zhuoyue.service;


import com.zhuoyue.bean.Hello;
import com.zhuoyue.bean.TestTable;

/**
 * @author gzd
 * @date 2017/10/26 19:31
 * @desc input the desc
 */
public interface HelloService {
    void insert(Hello hello);
    
    
    void insert2(TestTable testTable);
}
