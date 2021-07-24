package com.zh.springbootcrud.service;

import com.zh.springbootcrud.dao.UserDao;
import com.zh.springbootcrud.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    public UserDao userDao;

    public String login(){
        return "";
    }

    public List<UserEntity> getAllUser(){
        // 根据id降序
        return userDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    public List<UserEntity> getAllUserBySQL(){
        return userDao.getAllUserBySQL();
    }

    public UserEntity updateUserBySQL(UserEntity user) {
        return userDao.save(user);
    }

    public int deleteUserBySQL(String id) {
        return userDao.deleteUserBySQL(id);
    }

    public UserEntity addUserBySQL(UserEntity user) {
        // 若db中这个id对应的字段不存在，则插入
        // 若db中这个id对应的字段存在，则更新
        return userDao.save(user);
    }

    // 模糊查询，不分页
    public List getUserByNameSQL(String userName) {
        return userDao.getAllUserByNameSQL(userName);
    }

    public List getUserByNamePage(String userName,int currentPage,int pageSize) {
        List<UserEntity> result = null;
        // 构造自定义查询条件
        Specification<UserEntity> queryCondition = new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // Predicate是什么？
                // 这里面写相关的条件
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.like(root.get("userName"), "%" + userName+ "%"));
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        if(userName==null||userName==""){
            result = userDao.findAll();
        }else{
            // currentPage从0开始，如果前端第一页传的是1，则需要变为1-1
            result = userDao.findAll(queryCondition, PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "id"))).getContent();
        }
        return result;
    }

    /**
     * 先排序后分页
     * @param page
     * @param size
     * @param sort 排序的字段
     * @return
     */
    public Page<UserEntity> getUserSoreAndPageBySQL(String page, String size, String sort) {
        // springboot2.2.1（含）以上的版本Sort已经不能再实例化了，构造方法已经是私有的了！
        // Sort.by()可以一个或多个字段排序
        Sort mySort =  Sort.by(Sort.Direction.DESC, sort);
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), mySort);
        return userDao.findAll(pageable);
        // return userDao.getUserSoreAndPageBySQL(page,size,sort);
    }


}
