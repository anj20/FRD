package com.lcwd.rating.entities;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_rating")
public class Rating {
    @Id
    @Column(name = "ID")
    private String ratingId;
    @Column(name = "UserID")
    private String userId;
    @Column(name = "HotelID")
    private String hotelId;
    @Column(name = "Rating")
    private  int rating;
    @Column(name = "Feedback")
    private  String feedback;
}
