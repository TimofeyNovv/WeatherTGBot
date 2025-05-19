package com.example.weather_telegram_bot_0904.model.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_coord")
@Data
@NoArgsConstructor
public class UserCoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true) //unique = true означает, что значение уникально
    private Long userId;

    @Column(name = "chat_id", unique = true) //unique = true означает, что значение уникально
    private Long chatId;

    private Double latitude = 50.0;
    private Double longitude = 30.0;

}
