package dev.gemfire.spring.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapToMap implements Converter<Map<?, ?>, Map<?, ?>> {

    @Override
    public Map<?, ?> convert(Map<?, ?> source) {
        return source;
    }
}
