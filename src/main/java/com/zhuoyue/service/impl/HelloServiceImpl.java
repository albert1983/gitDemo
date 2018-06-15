package com.zhuoyue.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.bean.Hello;
import com.zhuoyue.bean.TestTable;
import com.zhuoyue.common.db.DataSourceNames;
import com.zhuoyue.common.db.annotation.DataSource;
import com.zhuoyue.common.log.annotation.ServiceLog;
import com.zhuoyue.dao.HelloMapper;
import com.zhuoyue.dao.TestTableMapper;
import com.zhuoyue.service.HelloService;
 

/**
 * @author gzd
 * @date 2017/10/26 19:34
 * @desc input the desc
 */
@Service
@Transactional(readOnly = true)
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloMapper helloDao;
    
    @Autowired
    private  TestTableMapper  testTableDao;
    

    @Override
    @Transactional
    //此处为AOP拦截Service记录异常信息。方法不需要加try-catch
    @ServiceLog(description = "查询用户")
    public void insert(Hello hello) {
        helloDao.insert(hello);
        //throw new ServiceException("dd");

    }

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public void insert2(TestTable testTable) {
		 
		testTableDao.insert(testTable);
	}

}
