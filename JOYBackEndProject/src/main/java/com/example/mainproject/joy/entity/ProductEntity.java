package com.example.mainproject.joy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productid;

    @Column(nullable = false)
    private String productname;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private long avalible;

    @Column(nullable = false)
    private String productcolor;

    @Column(nullable = false)
    private String gender;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] productimage;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] productrightsideview;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] productleftsideview;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] productbacksideview;

    
}


