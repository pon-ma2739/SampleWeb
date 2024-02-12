package com.example.demo.controller;


import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.LoginForm;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controllerクラス
 * 
 * @author Pon
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	/** セッション情報 */
	private final HttpSession session;

	/**
	 * 画面の初期表示を行います。
	 *
	 * @param model モデル
	 * @param form 入力情報
	 * @return ログイン画面
	 */
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		return "login";	
	}


	/**
	 * ログインエラー時にセッションからエラーメッセージを取得して、画面の表示を行います。
	 *
	 * @param model モデル
	 * @param form 入力情報
	 * @return ログイン画面
	 */
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());
		return "login";
	}
}