package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "title")
    private String title;

    @Column(name = "subTitle")
    private String subTitle;

    @Column(name = "price")
    private  double price;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "discount")
    private double discount;

    @Column(name = "bestseller")
    private int bestseller;

    @Column(name = "ratings")
    private int ratings;

    @Column(name = "rating_count")
    private int ratingCount;

    @Column(name = "instructor_id")
    private int instructorId;

    @Column(name = "purchases_count")
    private int purchasesCount;

    @Column(name = "course_link")
    private String courseLink;


    private int duration;

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "in_wishlist")
    private String inWishlist;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(name = "price_monthly")
    private double priceMonthly;

    @Column(name = "price_quarterly")
    private double priceQuarterly;

    @Column(name = "price_semiannually")
    private double priceSemiannually;

    @Column(name = "price_annually")
    private  double priceAnnually;





}
