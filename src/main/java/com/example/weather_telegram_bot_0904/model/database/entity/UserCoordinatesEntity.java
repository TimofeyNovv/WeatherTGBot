package com.example.weather_telegram_bot_0904.model.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_coord")
@Getter
@Setter
@NoArgsConstructor
public class UserCoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true) //unique = true означает, что значение уникально
    private Long userId; //

    private Double latitude = 50.0;
    private Double longitude = 30.0;


}
