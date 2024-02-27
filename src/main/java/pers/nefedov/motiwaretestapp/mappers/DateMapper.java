package pers.nefedov.motiwaretestapp.mappers;

import java.util.Date;

public interface DateMapper {
    String mapDateToString(Date date);
    Date mapToDate(String dateString);
}
