package com.blackstone.webappsorganizationsurvey.entity;


import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class FormFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "File name can not be null")
    private String name;

    @NotNull(message = "File type can not be null")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "file_type")
    private FileType fileType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Form form;

    @CreationTimestamp
    private LocalDateTime uploadedDate;
}
