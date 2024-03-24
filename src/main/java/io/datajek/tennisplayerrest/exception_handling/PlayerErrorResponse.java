package io.datajek.tennisplayerrest.exception_handling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerErrorResponse {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private String message;
}
