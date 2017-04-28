package com.zmc.repository;

import com.zmc.bean.User;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryRestResource定制模块的路径 localhost：port/user
 * 若这个不配置 默认为localhost：port/users  一般是实体类名小写并加s
 *
 * http://localhost:8088/users?page=0&size=2 第一页 每页为2条数据
 *
 * http://localhost:8088/users/7 post {"username":"zmc","password":"123"} add
 * http://localhost:8088/users/7 put {"username":"zmc","password":"zmc"} update
 * http://localhost:8088/users/7 delete    del
 */
//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * http://localhost:8088/users/search/findByUsername{?username}
     * @param username
     * @return
     */
    @RestResource(path = "findByName",rel = "findByUsername")
    @Query("select u from User u where u.username=?1")
    User findByUsername(@Param("username") String username);

    /*@Query("select username,password from user where username=?1")
    User findByUsername(@Param("username") String username);*/

    /**
     * http://localhost:8088/users/search/findByUsernameStartsWith{?username}
     * @param username 以开头查询
     * @return
     */
    List<User> findByUsernameStartsWith(@Param("username") String username);

    List<User> findByUsernameLike(@Param("username") String username);

    /**
     * @Query注解中表名用实体名
     * @return
     */
    @Query(value = "select u from User u")
    List<User> findAll();

}