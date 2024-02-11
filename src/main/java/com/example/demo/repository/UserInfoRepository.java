package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserInfo;



/**
 * ユーザー情報テーブルDAO
 * 
 * @author Pon
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
	// Repostiryであることを示す
	// JpaRepositoryの引数 使うEntity, 最初の値(PK)の型
}
