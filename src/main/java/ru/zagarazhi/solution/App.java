package ru.zagarazhi.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import ru.zagarazhi.solution.annotation.ParseIndexProcessor;
import ru.zagarazhi.solution.model.Car;
import ru.zagarazhi.solution.model.CarMaker;

public class App {
    @SneakyThrows
    public static void main(String[] args){
        Map<String, Integer> map = IOProcessor.readFile("CAR_DATA.csv");
        List<Car> carList = createCarList(map);
        
        taskFive(carList);
        taskSix(carList);
        taskSevenAndEight(carList);
    }

    public static List<Car> createCarList(Map<String, Integer> map){
        ParseIndexProcessor processor = new ParseIndexProcessor();
        List<Car> carList = new ArrayList<>();
        List<String> stringList = IOProcessor.toStringList(map);
        ListIterator<String> itt = stringList.listIterator();

        String line = itt.next();
        processor.registerClass(line, Car.class);
        Car temp;
        while(itt.hasNext()){
            line = itt.next();
            temp = processor.parseObject(line, Car.class, 4);
            if(!temp.hasEmptyField()) carList.add(temp);
        }
        return carList;
    }

    public static void taskFive(List<Car> carList){
        IOProcessor.writeFile(carList
            .stream()
            .collect(Collectors
                .groupingBy(Car::getColor, Collectors
                    .mapping(Car::toString, Collectors
                        .toList()))).entrySet(), "task6.txt");
    }

    public static void taskSix(List<Car> carList){
        IOProcessor.writeFile(carList
        .stream()
        .collect(Collectors
            .groupingBy(Car::getColor, Collectors
                .mapping(Car::toString, Collectors
                    .toList()))).entrySet(), "task6.txt");
    }

    public static void taskSevenAndEight(List<Car> carList){
        Map<String, List<Car>> groupByCarMaker = carList.stream()
                .collect(Collectors.groupingBy(
                        Car::getCarMake,
                        Collectors.toList()));
        List<CarMaker> carMakerList = new ArrayList<>();
        for(Map.Entry<String, List<Car>> entry: groupByCarMaker.entrySet()){
            List<Car> cars = entry.getValue();
            String carMaker = entry.getKey();
            CarMaker carMakerObject = new CarMaker();
            carMakerObject.setCarMakerName(carMaker);
            carMakerObject.setCarList(cars);
            carMakerList.add(carMakerObject);
        }
        IOProcessor.writeFile(carMakerList.stream().collect(Collectors.mapping(CarMaker::toString, Collectors.toList())), "task7.txt");
        
        String carMakerToConsole = groupByCarMaker.entrySet()
                     .stream()
                     .map(e -> e.getKey())
                     .filter(e -> e.length() != 0)
                     .collect(Collectors.joining(", "));
        System.out.println(carMakerToConsole);
        List<String> carMakerNames = new ArrayList<>();
        for(Map.Entry<String, List<Car>> entry: groupByCarMaker.entrySet()){
            List<Car> values = entry.getValue();
            String key = entry.getKey();
            if(values.size() >= 2){
                carMakerNames.add(key);
            }
        }
        
        carMakerNames = carMakerNames.stream().sorted().collect(Collectors.toList());
        
        ListIterator<String> iterMakers = carMakerNames.listIterator();
        Iterable<String> iterableMakers = () -> iterMakers;
        IOProcessor.writeFile(iterableMakers, "task8.txt");
    }
}
