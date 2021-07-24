package com.zh.springbootcrud.dao;

import com.zh.springbootcrud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor {
    /**
     * JpaRepository是Spring提供的非常强大的基本接口。
     *
     * https://blog.csdn.net/qq_40943786/article/details/80966170
     * @Repository、@Service、@Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。
     * @Repository、@Service、@Controller 和 @Component 将类标识为Bean
     *
     * https://my.oschina.net/u/3080373/blog/1828589
     * jpa是一种规范，它通过注解或者XML描述【对象-关系表】之间的映射关系，并将实体对象持久化到数据库中
     * 目的是让开发者从繁琐的JDBC和SQL代码中解脱出来。
     * JPA仅仅定义了一些接口，而接口是需要实现才能工作的。所以底层需要某种实现，而Hibernate就是实现了JPA接口的ORM框架
     *
     * https://segmentfault.com/a/1190000021755149
     * 我们原来使用JDBC连接来读写数据库，我们最常见的就是打开数据库连接、使用复杂的SQL语句进行读写、关闭连接，
     * 获得的数据又需要转换或封装后往外传，这是一个非常烦琐的过程。这时出现了Hibernate框架，它需要你创建一系列的持久化类，
     * 每个类的属性都可以简单的看做和一张数据库表的属性一一对应。这样我们不用在关注数据库，只需要持久化类就可以完成增删改查的功能。
     * 使我们的软件开发真正面向对象， 而不是面向混乱的代码。
     * 实体对象≠持久化对象。因为实体对象涵盖更广泛，它可以是持久化对象，也可以是内存中的任何对象。
     */
    /**
     * 查询
     * @return
     */
    @Query(value = "select * from user order by id desc",nativeQuery = true)
    public List<UserEntity> getAllUserBySQL();
    /**
     * 模糊查询
     */
    @Query(value = "select * from user u where u.user_name like %?1%",nativeQuery = true)
    public List<UserEntity> getAllUserByNameSQL(String userName);

    /**
     * 分页，排序查询，JpaRepository可以实现分页与排序，如果不使用JpaRepository
     * 只能自己写sql分页了
     * @param page
     * @param size
     * @param sort
     * @return
     */
    // @Query(value = "",nativeQuery = true)
    // public List getUserSoreAndPageBySQL(String page, String size, String sort);

    /**
     * 新增 JpaRepository可以新增，不然只能自己写sql
     * @param user
     * @return
     */
    // @Transactional
    // @Modifying
    // @Query(value = "insert  into user value() ",nativeQuery = true)
    // public int addUserBySQL(UserEntity user);

    /**
     * 语句中 ”?X” 称为索引参数，个数需要与方法定义的参数个数相一致，并且顺序也要一致
     * :xx 称为命名参数，这个与顺序无关，要么都使用索引参数形式，要么都使用命名参数的形式，不然无法运行
     * https://blog.csdn.net/Soulmate_Min/article/details/82755097
     * 如果JPA提示Executing an update/delete query，那是一定是因为没有加@Transactional和@Modifying。
     * 因为JPA没有事务支持，不能执行更新和删除操作。 所以反过来讲，就是在Service层或者Repository层上
     * 必须加@Transactional，来代表这是一个事务级别的操作，增删改查除了查都是事务级别的，就当这是一个规范也是ok的。
     */
    /**
     * 修改，这个方法没有使用，因为对于从body里获得的一个简单的对象，直接通过jpa的save方法能够更开的操作
     * @param id
     * @param userName
     * @return
     */
    @Transactional // 开启事务
    @Modifying //识别执行更新操作
    @Query(value = "update user u set u.user_name=:userName where id=:id",nativeQuery = true)
    public int updateBySQL(String id, String userName);

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "delete from user where id=?1",nativeQuery = true)
    public int deleteUserBySQL(String id);

}
