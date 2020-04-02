package com.hxszd.background.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @description: JSON序列化处理
 * @author: pig1etO
 * @create: 2020-04-02 16:01
 **/
public class MoneySerializer extends JsonSerializer<BigDecimal> {

    private final static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(df.format(bigDecimal));
    }
}
