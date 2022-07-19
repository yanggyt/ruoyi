package com.ruoyi.common.exception.user;

/**
 * 同浏览器 防止不同用户登录时
 * 后者覆盖前者的sessionid
 * 解决方案（同一浏览器中 第二个不同的用户登录时拒绝 需要先下线第一个用户）
 */
public class UserExistingException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserExistingException() {
        super("user.existing", null);
    }
}