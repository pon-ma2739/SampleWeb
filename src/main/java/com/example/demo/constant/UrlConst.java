package com.example.demo.constant;

/**
 * URL定数クラス
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
	
	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, SIGNUP, "/webjars/**" };
	
}
