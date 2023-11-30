package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private String title;

    private String subTitle;

    private  double price;

    private String coverImage;

    private double discount;

    private int bestseller;

    private int ratings;

    private int ratingCount;

    private int instructorId;

    private int purchasesCount;

    private String courseLink;

    private double duration;

    private String instructor;

    private String inWishlist;

    private double discountPrice;

    private double priceMonthly;

    private double priceQuarterly;

    private double priceSemiannually;

    private  double priceAnnually;





}
