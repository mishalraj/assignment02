package com.fable.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name="log", indexes = {@Index(name="idx_user_id", columnList = "user_id"),
@Index(name="idx_event_userid",columnList = "event_name,user_id")})
public class LogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="log_id", nullable =false, length = 20)
    private Long logId;

    @Column(name="unix_ts", nullable = true , length = 20)
    private Long unixTs;

    @Column(name="user_id", nullable = true , length = 20)
    private Long userId;

    @Column(name="event_name", nullable = true , length = 20)
    private String eventName;
}
