package ru.zagarazhi.solution.model;

import java.util.List;
import java.util.ListIterator;
import lombok.Data;
import ru.zagarazhi.solution.annotation.ParseIndex;

@Data
public class CarMaker {
    
    @ParseIndex(headerName = "List")
    private List<Car> carList;

    @ParseIndex(headerIndex = 0)
    private String carMakerName;

    public void setCarList(List<Car> carList){
        this.carList = carList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(this.carMakerName);
        sb.append(" = [");
        ListIterator<Car> itt = carList.listIterator();
        while(itt.hasNext()){
            sb.append(itt.next().toString());
            if(itt.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
