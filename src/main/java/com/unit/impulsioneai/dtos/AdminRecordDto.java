package com.unit.impulsioneai.dtos;

public record AdminRecordDto(String nome, String password, String email) {

    public static AdminRecordDto createDefault() {
        return new AdminRecordDto("Key admin da Silva", "123456789", "designtopamo@gmail.com");
    }
}
