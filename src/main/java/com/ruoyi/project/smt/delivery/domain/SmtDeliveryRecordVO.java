package com.ruoyi.project.smt.delivery.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发料记录对象 SmtDeliveryRecordVO
 *
 * @author popo
 * @date 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtDeliveryRecordVO extends BaseEntity {

    /**
     * 发货记录集合
     */
    private String deliveryDzlList;

    private Integer orderType;
}
