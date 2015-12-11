package at.htl.nfconnect.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Elias Salfinger on 11/12/15.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate entityDate) {
//        return Date.valueOf(entityDate);
        return entityDate == null ? null : Date.valueOf(entityDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseDate) {
        //return databaseDate.toLocalDate();
        return databaseDate == null ? null : databaseDate.toLocalDate();
    }
}
