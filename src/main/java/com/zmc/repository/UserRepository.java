package com.zmc.repository;

import com.zmc.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
  
    User findByUsername(@Param("username") String username);
    /*@Query("select username,password from user where username=#{username}")
    User findByUsername(@Param("username") String username);*/
}