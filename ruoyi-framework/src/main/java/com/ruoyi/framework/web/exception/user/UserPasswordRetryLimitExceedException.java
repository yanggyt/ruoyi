<<<<<<< HEAD
package com.ruoyi.framework.web.exception.user;

/**
 * 用户错误最大次数异常类
 * 
 * @author ruoyi
 */
public class UserPasswordRetryLimitExceedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount)
    {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount });
    }
}
=======
package com.ruoyi.framework.web.exception.user;

/**
 * 用户错误最大次数异常类
 * 
 * @author ruoyi
 */
public class UserPasswordRetryLimitExceedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount)
    {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount });
    }
}
>>>>>>> c404de177361c58256c3fe7ac8124ea9ac7f890d
