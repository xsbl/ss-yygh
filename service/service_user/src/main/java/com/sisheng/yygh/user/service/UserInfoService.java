package com.sisheng.yygh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sisheng.yygh.model.user.UserInfo;
import com.sisheng.yygh.vo.user.LoginVo;
import com.sisheng.yygh.vo.user.UserAuthVo;
import com.sisheng.yygh.vo.user.UserInfoQueryVo;

import java.util.Map;

/**
 * @author bobochang
 * @description
 * @created 2022/7/5-22:37
 **/
public interface UserInfoService  extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);

    UserInfo getByOpenid(String openId);

    void userAuth(Long userId, UserAuthVo userAuthVo);

    IPage<UserInfo> selectPage(Page<UserInfo> userInfoPage, UserInfoQueryVo userInfoQueryVo);

    void lock(Long userId, Integer status);

    Map<String, Object> show(Long userId);

    void approval(Long userId, Integer authStatus);
}
