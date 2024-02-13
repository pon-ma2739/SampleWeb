package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー情報テーブルEntityクラス
 * 
 * @author Pon
 */
@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {
	/** ログインID */
	@Id
	@Column(name = "login_id")
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
	@Convert(converter = UserStatusConverter.class)
	private UserStatusKind status;
	
	/** ユーザー権限 */
	@Convert(converter = UserAuthorityConverter.class)
	private AuthorityKind authority;

	/** 登録日時 */
	@Column(name = "create_time")
	private LocalDateTime createTime;

	/** 最終更新日時 */
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/**
	 * デフォルトコンストラクタ
	 */
	public UserInfo() {
	}

	/**
	 * ログイン失敗回数をインクリメントします。
	 *
	 * @return ログイン失敗回数がインクリメントされた、自身のインスタンス
	 */
	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(
				loginId, password, ++loginFailureCount, accountLockedTime,
				status, authority, createTime, updateTime
		);
	}

	/**
	 * ログイン失敗情報をリセットします。
	 *
	 * @return ログイン失敗情報がリセットされた、自身のインスタンス
	 */
	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(
				loginId, password, 0, null,
				status, authority, createTime, updateTime
		);
	}

	/**
	 * ログイン失敗回数、アカウントロック日時を更新し、アカウントロック状態に更新します。
	 *
	 * @return ログイン失敗回数、アカウントロック日時が更新された、自身のインスタンス
	 */
	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId, password, 0, LocalDateTime.now(),
				status, authority, createTime, updateTime
		);
	}
}
