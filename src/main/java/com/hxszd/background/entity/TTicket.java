package com.hxszd.background.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author pig1etO
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TTicket implements Serializable {
    
    private Long id;

//    private static final long serialVersionUID = 1L;
//
//    private Integer lineId;

    /**
     * 总库存
     */
    private Integer ticketNum;

    /**
     * 剩余库存
     */
//    private Integer leftStock;
//
//    private LocalDateTime createDate;
//
//    private LocalDateTime lastModified;


}
