package com.example.demo.constant;

/**
 * リクエストURL定数クラス
 * 
 * @author Pon
 */
public class UrlConst {
	
	/** ログイン画面 */
	public static final String LOGIN = "/login";
	
	/** ユーザー登録画面 */
	public static final String SIGNUP= "/signup";
	
	/** ユーザー登録画面 */
	public static final String MENU= "/menu";

	/** ユーザー一覧画面 */
	public static final String USER_LIST = "/userList";

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, "/webjars/**" };
	
}
