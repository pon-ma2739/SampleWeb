package com.example.demo.service;

import com.example.demo.dto.UserListInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import com.github.dozermapper.core.Mapper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

    /* ユーザー情報テーブルDAO */
    private final UserInfoRepository repository;

    /* Dozer Mapper */
    private final Mapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserListInfo> editUserList() {
        return toUserListInfos(repository.findAll());
    }

    private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
        var userListInfos = new ArrayList<UserListInfo>();
        for(UserInfo userInfo : userInfos) {
            var userListInfo = mapper.map(userInfo, UserListInfo.class);
            userListInfo.setStatus(userInfo.getStatus().getDisplayValue());
            userListInfo.setAuthority(userInfo.getAuthority().getDisplayValue());
            userListInfos.add(userListInfo);
        }

        return userListInfos;
    }
}
