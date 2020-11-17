package course.springdata.gsonlab.dtos;

import com.google.gson.annotations.Expose;

public class SupplierViewDto {

    @Expose
    private Long Id;
    @Expose
    private String Name;
    @Expose
    private int PartsCount;

    public SupplierViewDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPartsCount() {
        return PartsCount;
    }

    public void setPartsCount(int partsCount) {
        PartsCount = partsCount;
    }
}
