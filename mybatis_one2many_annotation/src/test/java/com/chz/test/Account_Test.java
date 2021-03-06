package com.chz.test;

import com.chz.dao.IAccountDao;
import com.chz.entity.Account;
import com.chz.entity.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Account_Test {
    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before//在测试方法执行之前执行
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        accountDao= sqlSession.getMapper(IAccountDao.class);//创建了一个IUserDao接口的实现类
    }

    @After//在测试方法执行之后
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    //查询方法
    public void selectMethod() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("-----------");
            System.out.println(account);
            System.out.println(account.getUser());
            System.out.println("-----------");
        }
    }

    @Test

    public void selectByIDMethod() {
        Account account = accountDao.findByID(45);
        System.out.println(account);
    }

    @Test
    public void setInfo(){
        List<AccountUser> info = accountDao.findInfo();
        for (AccountUser accountUser : info) {
            System.out.println(accountUser);
        }
    }



}
