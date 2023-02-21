package com.example.CoderBazi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFile {
    private String fileName;
    private String url;
    private long fileSize;
}
