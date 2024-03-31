package com.kpoy.housecams.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaDirectoryDto extends BaseItem {

    @Builder
    public MediaDirectoryDto(Integer id, OffsetDateTime createdDate, String filename, String path) {
        super(id, createdDate);
        this.filename = filename;
        this.path = path;
    }

    private String filename;
    private String path;

}
