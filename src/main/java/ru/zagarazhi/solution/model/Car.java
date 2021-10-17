package ru.zagarazhi.solution.model;

import lombok.Data;
import ru.zagarazhi.solution.annotation.ParseIndex;

@Data
public class Car {
    
    @ParseIndex(headerIndex = 0)
    private String carModel;

    @ParseIndex(headerIndex = 1)
    private String carMake;

    @ParseIndex(headerIndex = 2)
    private String carModelYear;

    @ParseIndex(headerIndex = 3)
    private String color;

    public boolean hasEmptyField(){
        return this.carModel.isBlank() 
            || this.carMake.isBlank() 
            || this.carModelYear.isBlank() 
            || this.color.isBlank();
    }

    @Override
    public String toString(){
        return "[Model: " + this.carModel + ", CarMake: " + this.carMake + ", Year: " + this.carModelYear + ", Color: " + this.color + "]";
    }
}
