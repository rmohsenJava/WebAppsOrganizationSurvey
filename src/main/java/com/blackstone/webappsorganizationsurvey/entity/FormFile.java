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
    @Column(nullable = false)
    private FileType fileType;

    @NotNull(message = "File size can not be null")
    @Column(nullable = false)
    private Long fileSize;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Form form;

    @CreationTimestamp
    private LocalDateTime uploadedDate;
}
