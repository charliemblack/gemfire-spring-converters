package dev.gemfire.spring.converters;

import org.apache.geode.cache.Region;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegionToMap implements Converter<Region<?, ?>, Map<?, ?>> {

    @Override
    public Map<?, ?> convert(Region<?, ?> source) {
        return source;
    }
}
