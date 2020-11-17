package course.springdata.gsonlab.servises.impl;


import com.google.gson.Gson;
import course.springdata.gsonlab.dtos.SupplierSeedDto;
import course.springdata.gsonlab.dtos.SupplierViewDto;
import course.springdata.gsonlab.entities.Supplier;
import course.springdata.gsonlab.repositories.SuppliersRepository;
import course.springdata.gsonlab.servises.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiseImpl implements SupplierService {

    private final String SUPPLIER_PATH = "src/main/resources/suppliers.json";

    private final SuppliersRepository suppliersRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiseImpl(SuppliersRepository suppliersRepository, Gson gson, ModelMapper modelMapper) {
        this.suppliersRepository = suppliersRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    @Override
    public String seedSupplierData() throws IOException {

        String content = String.join("",Files.readAllLines
                (Path.of(SUPPLIER_PATH)));
        SupplierSeedDto [] supplierSeedDto =
                this.gson.fromJson(content,SupplierSeedDto[].class);
        for (SupplierSeedDto supplierDto : supplierSeedDto) {
            Supplier supplier =
                    this.modelMapper.map(supplierDto,Supplier.class);
            this.suppliersRepository.saveAndFlush(supplier);
        }
        return "Successfully seeded Supplier in DB! ";
    }

    @Override
    public String getLocalSuppliers() throws IOException {

        List<Supplier> supplierList =
                this.suppliersRepository.findAllByImporterIsFalse();

        List<SupplierViewDto> supplierViewDtos = new ArrayList<>();

        for (Supplier supplier : supplierList) {

            SupplierViewDto supplierViewDto =
                    this.modelMapper.map(supplier,SupplierViewDto.class);
            supplierViewDto.setPartsCount(supplier.getParts().size());
            supplierViewDtos.add(supplierViewDto);
        }

        String toJson = this.gson.toJson(supplierViewDtos);

        FileWriter fileWriter = new FileWriter("local-suppliers.json");

        return toJson;
    }
}
