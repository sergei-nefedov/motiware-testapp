package pers.nefedov.motiwaretestapp.mappers;

import org.springframework.stereotype.Component;
import pers.nefedov.motiwaretestapp.models.Condition;
@Component
public class ConditionMapperImpl implements ConditionMapper {
    @Override
    public Condition mapToCondition(String condition) {
        return Condition.valueOf(condition);
    }
}
