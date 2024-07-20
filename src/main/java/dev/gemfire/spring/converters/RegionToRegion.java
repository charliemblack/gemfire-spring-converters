package dev.gemfire.spring.converters;

import org.apache.geode.cache.Region;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegionToRegion implements Converter<Region<?, ?>, Region<?, ?>> {

    @Override
    public Region<?, ?> convert(Region<?, ?> source) {
        return source;
    }
}
