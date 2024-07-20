package dev.gemfire.spring.converters;

import org.apache.geode.cache.Region;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapToRegion implements Converter<Map<?, ?>, Region<?, ?>> {

    @Override
    public Region<?, ?> convert(Map<?, ?> source) {
        if (source instanceof Region) {
            return (Region) source;
        }
        return null;
    }
}
