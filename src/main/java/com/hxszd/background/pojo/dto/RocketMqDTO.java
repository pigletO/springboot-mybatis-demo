package com.hxszd.background.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-23 13:47
 **/
@Data
public class RocketMqDTO {
    @NotEmpty(groups = RocketMqDTO.validate.class)
    String topic;
    @NotEmpty(groups = RocketMqDTO.validate.class)
    String tags;
    @NotEmpty(groups = RocketMqDTO.validate.class)
    String body;

    public interface validate{}
}
