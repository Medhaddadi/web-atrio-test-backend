package com.gestionEmployer.Web_atrio_test.Utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {
    public static int getAge(LocalDate dateNaissance){
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}
