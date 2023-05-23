package com.beesmart.management.activities.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Converter
public class JsonListConverter implements AttributeConverter<List<AvailableActivityTerm>, String> {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(List<AvailableActivityTerm> attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to convert List<MyObject> to JSON string.", e);
    }
  }

  @Override
  public List<AvailableActivityTerm> convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to convert JSON string to List<MyObject>.", e);
    }
  }
}
