package com.blackstone.webappsorganizationsurvey.dto;


import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class FileResponse {

    private Integer id;

    private String name;

    private String type;

    private FileType fileType;

    private Long fileSize;
}
