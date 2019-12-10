package com.ruoyi.project.smt.ship.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品出货对象 smt_product_ship
 *
 * @author popo
 * @date 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtProductShipVO {
    private static final long serialVersionUID = 1L;

    /**
     * 总备品数
     */
    private Integer sumBak;

    /**
     * 总出货数
     */
    private Integer sumShipQty;

}
