package com.app.dto;

import jakarta.validation.constraints.NotEmpty;

public record Entries(
         Long id,

         @NotEmpty(message = "Entry shouldn't be empty")
         String entry,

         com.app.dto.Status status){
}
