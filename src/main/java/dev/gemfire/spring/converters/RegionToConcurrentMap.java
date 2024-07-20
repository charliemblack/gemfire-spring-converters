package dev.gemfire.spring.converters;

import org.apache.geode.cache.Region;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;

@Component
public class RegionToConcurrentMap implements Converter<Region<?, ?>, ConcurrentMap<?, ?>> {
    @Override
    public ConcurrentMap<?, ?> convert(Region<?, ?> source) {
        return source;
    }
}

