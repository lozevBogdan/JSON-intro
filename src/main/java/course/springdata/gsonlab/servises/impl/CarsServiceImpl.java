package course.springdata.gsonlab.servises.impl;

import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.*;
import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.repositories.CarsRepository;
import course.springdata.gsonlab.repositories.PartsRepository;
import course.springdata.gsonlab.servises.CarsServise;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.spi.AudioFileWriter;
import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@Transactional
public class CarsServiceImpl implements CarsServise {

    private final String CARSDATA_PATH = "src/main/resources/cars.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;

    @Autowired
    public CarsServiceImpl
            (ModelMapper modelMapper, Gson gson, CarsRepository carsRepository, PartsRepository partsRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
    }

    @Override

    public String seedCarsData() throws IOException {

        String content = String.join("",
                Files.readAllLines(Path.of(CARSDATA_PATH)));

        CarSeedDto [] carSeedDto =
                this.gson.fromJson(content,CarSeedDto[].class);

        List<Car> seededCars = new ArrayList<>();

        for (CarSeedDto seedDto : carSeedDto) {

            Car car = this.modelMapper.map(seedDto,Car.class);

            car.setParts(getRandomListOfParts());

            seededCars.add(car);
            this.carsRepository.saveAndFlush(car);

        }

       // List<Car> savedCars = this.carsRepository.findAll();

        return "Successfully seeded Cars data in DB!";
    }

    @Override
    public String carsFromMake(String make) throws IOException {

        List<Car> cars = this.carsRepository
                .findAllByMakeOrderByModelAndTravelledDistance(make);

        List<CarViewDto> carViewDtoList = new ArrayList<>();

        for (Car car : cars) {
            CarViewDto carViewDto = this.modelMapper
                    .map(car,CarViewDto.class);
            carViewDtoList.add(carViewDto);
        }


        String toJson = this.gson.toJson(carViewDtoList);


        FileWriter fileWriter = new FileWriter("toyota-cars.json");

        fileWriter.write(toJson);
        fileWriter.close();

        return toJson;
    }

    @Override
    public String carsWithListOfParts() throws IOException {

        List<Car> cars = this.carsRepository.getAllCars();

        List<CarInfoDto> carInfoDtoList = new ArrayList<>();

        for (Car car : cars) {

            List<Part> partList = car.getParts();
            List<PartsViewDto> partsViewDtoList = new ArrayList<>();

            for (Part part : partList) {
                PartsViewDto partsViewDto =
                this.modelMapper.map(part,PartsViewDto.class);
                partsViewDtoList.add(partsViewDto);
            }


            CarInfoDto carInfoDto =
                    this.modelMapper.map(car,CarInfoDto.class);

            carInfoDto.setParts(partsViewDtoList);

            carInfoDtoList.add(carInfoDto);

        }


        CarInfo carInfo = new CarInfo();
        carInfo.setCars(carInfoDtoList);

        String toJson = this.gson.toJson(carInfo);

        FileWriter fileWriter = new FileWriter("cars-and-parts.json");
        fileWriter.write(toJson);;
        fileWriter.close();




        return toJson;
    }

    private List<Part> getRandomListOfParts() {

        List<Part> allParts = this.partsRepository.findAll();
        Random random = new Random();
        List<Part> listOfParts = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(allParts.size());
            listOfParts.add(allParts.get(index));
        }
        return listOfParts;
    }
}
