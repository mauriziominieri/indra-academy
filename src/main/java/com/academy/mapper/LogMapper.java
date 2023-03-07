package com.academy.mapper;

import com.academy.dto.LogDto;
import com.academy.model.Log;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Mapper(componentModel = "spring")
public interface LogMapper {
    LogDto toDto(Log log);
    Log toModel(LogDto logDto);
    List<LogDto> toDtos(List<Log> logList);
    List<Log> toModels(List<LogDto> logDtoList);
}
