package com.example.exam.seeders;

import com.example.exam.model.entity.Condition;
import com.example.exam.model.entity.ConditionEnum;
import com.example.exam.repositories.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConditionSeeder implements CommandLineRunner {

    public final static String EXCELLENT_DESCRIPTION = "In perfect condition";
    public final static String GOOD_DESCRIPTION = "Some signs of wear and tear or minor defects";
    public final static String ACCEPTABLE_DESCRIPTION = "The item is fairly worn but continues to function properly";


    private final ConditionRepository conditionRepository;

    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (this.conditionRepository.count() == 0) {
            Arrays.stream(ConditionEnum.values())
                    .forEach(conditionEnum -> {
                        Condition condition = new Condition();
                        condition.setName(conditionEnum);
                        switch (conditionEnum) {
                            case EXCELLENT -> condition.setDescription(EXCELLENT_DESCRIPTION);
                            case GOOD -> condition.setDescription(GOOD_DESCRIPTION);
                            case ACCEPTABLE -> condition.setDescription(ACCEPTABLE_DESCRIPTION);
                        }
                        this.conditionRepository.save(condition);
                    });
        }
    }
}
