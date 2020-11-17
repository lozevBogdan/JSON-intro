package course.springdata.gsonlab.servises.impl;

import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.CarSeedDto;
import course.springdata.gsonlab.dtos.CarViewDto;
import course.springdata.gsonlab.entities.Car;
import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.repositories.CarsRepository;
import course.springdata.gsonlab.repositories.PartsRepository;
import course.springdata.gsonlab.servises.CarsServise;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.spi.AudioFileWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
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

        for (CarSeedDto seedDto : carSeedDto) {

            Car car = this.modelMapper.map(seedDto,Car.class);

            car.setParts(getRandomListOfParts());

            this.carsRepository.saveAndFlush(car);

        }

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
