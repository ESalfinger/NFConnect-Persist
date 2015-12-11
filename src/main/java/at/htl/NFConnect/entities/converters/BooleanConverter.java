package at.htl.nfconnect.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Elias Salfinger on 10/12/15.
 */
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean == true)
            return 1;

        else
            return 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        if (integer == 1)
            return true;
        else
            return false;
    }

}