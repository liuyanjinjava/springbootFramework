package com.futao.springbootdemo.dao;

import com.futao.springbootdemo.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author futao
 * Created on 2018/9/20-16:00.
 */
public interface UserDao {


//    /**
//     * 查询用户列表
//     *
//     * @param mobile
//     * @param start
//     * @param limit
//     * @return
//     */
//    @Select("select " +
//            "* " +
//            "from futao_user " +
//            "where mobile like '%${mobile}%' " +
//            "order by createtime desc " +
//            "limit #{start},#{limit}")
//    List<User> list(@Param("mobile") String mobile, @Param("start") int start, @Param("limit") int limit);


    /**
     * 查询用户列表
     *
     * @param sql
     * @return
     */
    @Select("${sql}")
    List<User> list(@Param("sql") String sql);

    /**
     * 按表名查询数据量
     *
     * @param tableName
     * @return
     */
    @Select("select count(*) from ${tableName}")
    int total(@Param("tableName") String tableName);

    /**
     * 用户注册
     *
     * @param id
     * @param username
     * @param password
     * @param age
     * @param mobile
     * @param email
     * @param address
     * @param createTime
     * @param lastmodifytime
     * @return
     */
    int addUser(@Param("id") String id, @Param("username") String username, @Param("password") String password, @Param("age") String age,
                @Param("mobile") String mobile, @Param("email") String email, @Param("address") String address,
                @Param("createtime") Timestamp createTime, @Param("lastmodifytime") Timestamp lastmodifytime);

    /**
     * 通过手机号查询用户信息
     *
     * @param mobile 用户手机号
     * @return
     */
    User getUserByMobile(@Param("mobile") String mobile);

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    User getUserById(String id);


    /**
     * 通过手机号和密码查询用户信息
     *
     * @param mobile   用户手机号
     * @param password 密码
     * @return
     */
    User getUserByMobileAndPwd(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 通过用户名与密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * " +
            "from " +
            "futao_user " +
            "where " +
            "username=#{username} " +
            "and " +
            "password=#{password}")
    User getUserByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 通过email查询正常的用户,如果没查到数据返回的是null
     *
     * @param email
     * @param status
     * @return
     */
    @Select("select id from futao_user where email=#{email} and status=#{status}")
    String getNormalUserByEmail(@Param("email") String email, @Param("status") int status);


    /**
     * 预注册
     *
     * @param id
     * @param email
     * @param status
     * @param createTime
     * @param lastModifyTime
     */
    @Insert("insert into futao_user(id,email,status,createTime,lastModifyTime) " +
            "select #{id},#{email},#{status},#{createTime},#{lastModifyTime} " +
            "where not exists(" +
            "select id from futao_user where email=#{email}" +
            ")")
    void preRegister(@Param("id") String id,
                     @Param("email") String email,
                     @Param("status") int status,
                     @Param("createTime") Timestamp createTime,
                     @Param("lastModifyTime") Timestamp lastModifyTime);


    /**
     * 用户通过邮箱进行注册
     *
     * @param username
     * @param password
     * @param age
     * @param mobile
     * @param address
     * @param status
     * @param sex
     * @param email
     */
    @Update("update futao_user " +
            "set username=#{username},password=#{password},age=#{age},mobile=#{mobile},address=#{address},status=#{status},sex=#{sex} " +
            "where email=#{email}")
    void registerByEmail(@Param("username") String username,
                         @Param("password") String password,
                         @Param("age") int age,
                         @Param("mobile") String mobile,
                         @Param("address") String address,
                         @Param("status") int status,
                         @Param("sex") int sex,
                         @Param("email") String email
    );

}
