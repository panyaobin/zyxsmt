package com.ruoyi.common.constant;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 通用物料类型
     */
    public static final Integer BOM_TYPE_FPC = 1;
    public static final Integer BOM_TYPE_DZL = 2;

    /**
     * 通用物料类型名称
     */
    public static final String FPC_TYPE_NAME = "FPC";

    /**
     * 出货类型 1.出货  2.退货
     */
    public static final Integer SHIP_TYPE_SHIP = 1;
    public static final Integer SHIP_TYPE_RETURNS = 2;

    /**
     * 发料单号首单号
     */
    public static final Integer DELIVERY_NO_START = 100000;

    /**
     * 退货单号首单号
     */
    public static final Integer RETURNS_NO_START = 900000;

    /**
     * 出货单号首单号
     */
    public static final Integer SHIP_NO_START = 100000;


    /**
     * 客户对账单号
     */
    public static final Integer CUS_RECONCILIATION_NO = 800000;

    /**
     * 付款申请单号
     */
    public static final Integer PAYMENT_APPLY_NO = 810000;

}
