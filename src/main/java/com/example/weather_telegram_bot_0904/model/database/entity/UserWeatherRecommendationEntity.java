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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserCoordinatesEntity user;

    @Column(name = "min_value")
    private Integer minValue;

    @Column(name = "max_value")
    private Integer maxValue;

    private String recommendation;

    public void setUser(UserCoordinatesEntity user) {
        this.user = user;
    }
}