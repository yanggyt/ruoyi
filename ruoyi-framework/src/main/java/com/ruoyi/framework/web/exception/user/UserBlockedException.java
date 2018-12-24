<<<<<<< HEAD
package com.ruoyi.framework.web.exception.user;

/**
 * 用户锁定异常类
 * 
 * @author ruoyi
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException(String reason)
    {
        super("user.blocked", new Object[] { reason });
    }
}
=======
package com.ruoyi.framework.web.exception.user;

/**
 * 用户锁定异常类
 * 
 * @author ruoyi
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException(String reason)
    {
        super("user.blocked", new Object[] { reason });
    }
}
>>>>>>> c404de177361c58256c3fe7ac8124ea9ac7f890d
