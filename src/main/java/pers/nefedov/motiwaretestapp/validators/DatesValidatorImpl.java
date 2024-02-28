package pers.nefedov.motiwaretestapp.validators;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class DatesValidatorImpl implements DatesValidator {
    @Override
    public boolean datesIsCorrect(Date firstDate, Date secondDate) {
        return secondDate.after(firstDate) || secondDate.equals(firstDate);
    }
}
