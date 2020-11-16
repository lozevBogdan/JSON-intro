//package course.springdata.gsonlab.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.URL;
//
//import javax.persistence.Entity;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class Post extends BaseEntity {
//
//    @NonNull
//    @NotNull
//    @Length(min = 3,max = 80,
//            message = "Title must be minimum 3 and maximum 80 characters long.")
//    private String title;
//
//    @NonNull
//    @NotNull
//    @Length(min = 3,max = 80)
//    private String content;
//
//    @NonNull
//    @NotNull
//    @URL
//    private String imageUrl;
//
//    @NonNull
//    @NotNull
//    @Length(min = 3,max = 80)
//    private String author;
//
//    private LocalDateTime created = LocalDateTime.now();
//    private LocalDateTime modified = LocalDateTime.now();
//
//
//}
