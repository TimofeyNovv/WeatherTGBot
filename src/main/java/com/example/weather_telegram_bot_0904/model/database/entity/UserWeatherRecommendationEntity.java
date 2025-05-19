package com.example.weather_telegram_bot_0904.model.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_recomend")
@Data
@NoArgsConstructor
public class UserWeatherRecommendationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "min_value", unique = true)
    private Integer minValue;

    @Column(name = "max_value", unique = true)
    private Integer maxValue;

    private String recommendation = "Для этого диапазона чисел пока что не было рекомендаций";


}
