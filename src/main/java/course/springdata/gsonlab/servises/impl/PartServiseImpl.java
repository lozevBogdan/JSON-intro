package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.PartSeedDto;
import course.springdata.gsonlab.entities.Part;
import course.springdata.gsonlab.entities.Supplier;
import course.springdata.gsonlab.repositories.PartsRepository;
import course.springdata.gsonlab.repositories.SuppliersRepository;
import course.springdata.gsonlab.servises.PartsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

@Service
public class PartServiseImpl implements PartsService {


    private final String PARTS_PATH = "src/main/resources/parts.json";

    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final SuppliersRepository suppliersRepository;


    @Autowired
    public PartServiseImpl(PartsRepository partsRepository,
                           ModelMapper modelMapper, Gson gson, SuppliersRepository suppliersRepository) {
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;

        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public String seedPartData() throws IOException {

        String content = String.join("",
                Files.readAllLines(Path.of(PARTS_PATH)));

        PartSeedDto [] partSeedDto =
                this.gson.fromJson(content,PartSeedDto[].class);

        for (PartSeedDto partDto : partSeedDto) {

            Part part = this.modelMapper
                    .map(partDto,Part.class);

            part.setSupplier(getRanadomSupplier());

            this.partsRepository.saveAndFlush(part);

        }


        return "Successfully seeded Part data in DB! ";
    }

    private Supplier getRanadomSupplier() {

        Random random = new Random();

        List<Supplier> allSuppliers = this.suppliersRepository.findAll();

        int index = random.nextInt(allSuppliers.size());

        return allSuppliers.get(index);
    }
}
