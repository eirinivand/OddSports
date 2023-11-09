package com.evandorou.oddsports.converter;

import com.evandorou.oddsports.model.Sport;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Sport, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Sport status) {
        if (status == null) {
            return null;
        }
        return status.getId();
    }

    @Override
    public Sport convertToEntityAttribute(Integer statusValue) {
        if (statusValue == null) {
            return null;
        }
        return Sport.fromId(
                statusValue);
    }
}
