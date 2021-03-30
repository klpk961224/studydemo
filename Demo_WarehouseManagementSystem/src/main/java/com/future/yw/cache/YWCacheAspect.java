package com.future.yw.cache;

import com.future.wms.cache.CachePool;
import com.future.yw.model.entity.YwCustomer;
import com.future.yw.model.entity.YwGoods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author evanliu
 * @create 2021-03-30 17:50
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class YWCacheAspect {
    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(YWCacheAspect.class);

    /**
     * 声明一个缓存容器
     */
    private Map<String, Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;


    /**
     * 声明客户的切面表达式
     */
    private static final String POINTCUT_CUSTOMER_ADD = "execution(* com.future.yw.service.impl.YwCustomerServiceImpl.save(..))";
    private static final String POINTCUT_CUSTOMER_UPDATE = "execution(* com.future.yw.service.impl.YwCustomerServiceImpl.updateById(..))";
    private static final String POINTCUT_CUSTOMER_GET = "execution(* com.future.yw.service.impl.YwCustomerServiceImpl.getById(..))";
    private static final String POINTCUT_CUSTOMER_DELETE = "execution(* com.future.yw.service.impl.YwCustomerServiceImpl.removeById(..))";
    private static final String POINTCUT_CUSTOMER_BATCHDELETE = "execution(* com.future.yw.service.impl.YwCustomerServiceImpl.removeByIds(..))";

    private static final String CACHE_CUSTOMER_PROFIX = "customer:";

    /**
     * 添加客户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_CUSTOMER_ADD)
    public Object cacheCustomerAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        YwCustomer object = (YwCustomer) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询客户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_CUSTOMER_GET)
    public Object cacheCustomerGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到客户对象" + CACHE_CUSTOMER_PROFIX + object);
            return res1;
        } else {
            log.info("未从缓存里面找到客户对象，从数据库中查询并放入缓存");
            YwCustomer res2 = (YwCustomer) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新客户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_CUSTOMER_UPDATE)
    public Object cacheCustomerUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        YwCustomer customerVo = (YwCustomer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            YwCustomer customer = (YwCustomer) CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX + customerVo.getId());
            if (null == customer) {
                customer = new YwCustomer();
            }
            BeanUtils.copyProperties(customerVo, customer);
            log.info("客户对象缓存已更新" + CACHE_CUSTOMER_PROFIX + customerVo.getId());
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + customer.getId(), customer);
        }
        return isSuccess;
    }

    /**
     * 删除客户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_CUSTOMER_DELETE)
    public Object cacheCustomerDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX + id);
        }
        return isSuccess;
    }

    /**
     * 批量删除客户切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_CUSTOMER_BATCHDELETE)
    public Object cacheCustomerBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            for (Serializable id : idList) {
                // 删除缓存
                CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX + id);
                log.info("客户对象缓存已删除" + CACHE_CUSTOMER_PROFIX + id);
            }
        }
        return isSuccess;
    }


    /**
     * 声明商品的切面表达式
     */
    private static final String POINTCUT_GOODS_ADD = "execution(* com.future.yw.service.impl.YwGoodsServiceImpl.save(..))";
    private static final String POINTCUT_GOODS_UPDATE = "execution(* com.future.yw.service.impl.YwGoodsServiceImpl.updateById(..))";
    private static final String POINTCUT_GOODS_GET = "execution(* com.future.yw.service.impl.YwGoodsServiceImpl.getById(..))";
    private static final String POINTCUT_GOODS_DELETE = "execution(* com.future.yw.service.impl.YwGoodsServiceImpl.removeById(..))";

    private static final String CACHE_GOODS_PROFIX = "goods:";

    /**
     * 添加商品切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GOODS_ADD)
    public Object cacheGoodsAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        YwGoods object = (YwGoods) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_GOODS_PROFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询商品切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GOODS_GET)
    public Object cacheGoodsGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_GOODS_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到商品对象" + CACHE_GOODS_PROFIX + object);
            return res1;
        } else {
            log.info("未从缓存里面找到商品对象，从数据库中查询并放入缓存");
            YwGoods res2 = (YwGoods) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_GOODS_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新商品切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GOODS_UPDATE)
    public Object cacheGoodsUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        YwGoods goodsVo = (YwGoods) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            YwGoods goods = (YwGoods) CACHE_CONTAINER.get(CACHE_GOODS_PROFIX + goodsVo.getId());
            if (null == goods) {
                goods = new YwGoods();
            }
            BeanUtils.copyProperties(goodsVo, goods);
            log.info("商品对象缓存已更新" + CACHE_GOODS_PROFIX + goodsVo.getId());
            CACHE_CONTAINER.put(CACHE_GOODS_PROFIX + goods.getId(), goods);
        }
        return isSuccess;
    }

    /**
     * 删除商品切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GOODS_DELETE)
    public Object cacheGoodsDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_GOODS_PROFIX + id);
        }
        return isSuccess;
    }


}
