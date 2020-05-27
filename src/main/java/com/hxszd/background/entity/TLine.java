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
public class TLine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线路名称：k107
     */
    private String name;

    private LocalDateTime createDate;

    private LocalDateTime lastModified;

    private Integer abc;


}
