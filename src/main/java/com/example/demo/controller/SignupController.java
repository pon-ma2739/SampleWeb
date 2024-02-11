package com.example.demo.controller;


import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SignupMessage;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Controller
 * 
 * @author Pon
 */
@Controller
@RequiredArgsConstructor
public class SignupController {
	
	/** ユーザー登録画面 Service */
	private final SignupService  service;

	/** メッセージソース */
	private final MessageSource messageSource;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 * 
	 * @author Pon
	 */
	@GetMapping("/signup")
	public String view(Model model, SignupForm form) {
		return "signup";
	}
	
	
	 
	/**
	 * ユーザー登録
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @param bdResult 入力チェック結果
	 * @return 表示画面
	 * 
	 * @author Pon
	 */
	@PostMapping("/signup")
	public void signup(Model model, @Validated SignupForm form, BindingResult bdResult) {
		/** バリデーションの結果がエラーか判定 */
		if(bdResult.hasErrors()) {
			editGuideMessage(model,MessageConst.FORM_ERROR,true);
			return;
		}
		Optional<UserInfo> userInfoOpt =  service.registUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
		editGuideMessage(model, signupMessage.getMessageId(), signupMessage.isError());
	}
	
	/**
	 * 
	 * 画面に表示するガイドメッセージを設定する
	 * 
	 * @param model モデル
	 * @param messageId メッセージID
	 * @param isError エラー有無
	 * 
	 * @author Pon
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		String message = AppUtil.getMesssage(messageSource, messageId);
		model.addAttribute("message", message);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * ユーザー情報登録の結果メッセージキーを判断する
	 * 
	 * @param userInfoOpt ユーザー登録情報(登録済みの場合はEmpty)
	 * @return メッセージキー
	 * 
	 * @author Pon
	 */
	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCEED;
		}
	}
}