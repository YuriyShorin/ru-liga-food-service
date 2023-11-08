package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Модель Id
 */
@Data
@AllArgsConstructor
public class Id {

    private UUID id;
}
