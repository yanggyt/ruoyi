<<<<<<< HEAD
package com.ruoyi.framework.web.exception.user;

/**
 * 角色锁定异常类
 * 
 * @author ruoyi
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException(String reason)
    {
        super("role.blocked", new Object[] { reason });
    }
}
=======
package com.ruoyi.framework.web.exception.user;

/**
 * 角色锁定异常类
 * 
 * @author ruoyi
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException(String reason)
    {
        super("role.blocked", new Object[] { reason });
    }
}
>>>>>>> c404de177361c58256c3fe7ac8124ea9ac7f890d
