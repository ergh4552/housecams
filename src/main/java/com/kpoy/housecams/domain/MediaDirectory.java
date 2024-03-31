package com.kpoy.housecams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDirectory {
    @Id
    private Integer id;
    private String path;
    private String filename;
    private Timestamp createdDate;

}
