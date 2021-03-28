package com.future.wms.cache;

import com.future.wms.model.entity.SysDept;
import com.future.wms.model.entity.SysUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author evanliu
 * @create 2021-03-27 18:11
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    /**
     * 声明一个缓存容器
     */
    private Map<String, Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;

    /**
     * 声明部门的切面表达式
     */
    private static final String POINTCUT_DEPT_ADD = "execution(* com.future.wms.service.impl.SysDeptServiceImpl.save(..))";
    private static final String POINTCUT_DEPT_UPDATE = "execution(* com.future.wms.service.impl.SysDeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET = "execution(* com.future.wms.service.impl.SysDeptServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_DELETE = "execution(* com.future.wms.service.impl.SysDeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX = "dept:";

    /**
     * 添加部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_ADD)
    public Object cacheDeptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        SysDept object = (SysDept) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到部门对象" + CACHE_DEPT_PROFIX + object);
            return res1;
        } else {
            log.info("未从缓存里面找到部门对象，从数据库中查询并放入缓存");
            SysDept res2 = (SysDept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        SysDept deptVo = (SysDept) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            SysDept dept = (SysDept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + deptVo.getId());
            if (null == dept) {
                dept = new SysDept();
            }
            BeanUtils.copyProperties(deptVo, dept);
            log.info("部门对象缓存已更新" + CACHE_DEPT_PROFIX + deptVo.getId());
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);
        }
        return isSuccess;
    }

    /**
     * 删除部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX + id);
        }
        return isSuccess;
    }

    /**
     * 声明用户的切面表达式
     */
    private static final String POINTCUT_USER_UPDATE = "execution(* com.future.wms.service.impl.SysUserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_ADD = "execution(* com.future.wms.service.impl.SysUserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET = "execution(* com.future.wms.service.impl.SysUserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_DELETE = "execution(* com.future.wms.service.impl.SysUserServiceImpl.removeById(..))";

    private static final String CACHE_USER_PROFIX = "user:";

    /**
     * 添加用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        SysUser object = (SysUser) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_USER_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到用户对象" + CACHE_USER_PROFIX + object);
            return res1;
        } else {
            log.info("未从缓存里面找到用户对象，从数据库中查询并放入缓存");
            SysUser res2 = (SysUser) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        SysUser userVo = (SysUser) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            SysUser user = (SysUser) CACHE_CONTAINER.get(CACHE_USER_PROFIX + userVo.getId());
            if (null == user) {
                user = new SysUser();
            }
            BeanUtils.copyProperties(userVo, user);
            log.info("用户对象缓存已更新" + CACHE_USER_PROFIX + userVo.getId());
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + user.getId(), user);
        }
        return isSuccess;
    }

    /**
     * 删除用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_DELETE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX + id);
        }
        return isSuccess;
    }

}
