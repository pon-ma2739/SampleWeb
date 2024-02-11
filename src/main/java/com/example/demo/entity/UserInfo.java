package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー情報テーブル Entity
 * 
 * @author Pon
 */
@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {
	/** ログインID */
	@Id												// PKを示す
	@Column(name = "login_id")		// カラム名と紐づける
	private String loginId;
	
	/** パスワード */
	private String password;
	
	/** ログイン失敗回数 */
	@Column(name = "login_failure_count")
	private int loginFailureCount;

	/** アカウントロック日時 */
	@Column(name = "account_locked_time")
	private LocalDateTime accountLockedTime;
	
	/** 利用可能か(true:利用可能) */
	@Column(name = "is_disabled")
	private boolean isDisabled;
	
	/** ユーザー権限 */
	@Column()
	private String authority;
	
	/** 引数なしコンストラクタ */
	public UserInfo() {
	}
	
	/**
	 * ログイン失敗回数をインクリメントする
	 * 
	 * @return ログイン失敗回数がインクリメントされたUserInfo
	 * 
	 * @author Pon
	 */
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(loginId, password, ++loginFailureCount, accountLockedTime, isDisabled, authority);
	}
	
	/**
	 * ログイン失敗情報をリセットする
	 * 
	 * @return ログイン失敗情報がリセットされたUserInfo
	 * 
	 * @author Pon
	 */
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(loginId, password, 0, null, isDisabled, authority);
	}
	
	/**
	 * アカウントロック状態を更新する
	 * 
	 * @return ログイン失敗回数、アカウントロック日時が更新されたUserInfo
	 * 
	 * @author Pon
	 */
	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId, password, 0, LocalDateTime.now(), isDisabled, authority);
	}
}
