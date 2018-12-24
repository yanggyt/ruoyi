<<<<<<< HEAD
package com.ruoyi.framework.web.exception.user;

/**
 * 用户错误记数异常类
 * 
 * @author ruoyi
 */
public class UserPasswordRetryLimitCountException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount)
    {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
=======
package com.ruoyi.framework.web.exception.user;

/**
 * 用户错误记数异常类
 * 
 * @author ruoyi
 */
public class UserPasswordRetryLimitCountException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount)
    {
        super("user.password.retry.limit.count", new Object[] { retryLimitCount });
    }
}
>>>>>>> c404de177361c58256c3fe7ac8124ea9ac7f890d
