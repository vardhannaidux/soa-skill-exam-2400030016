package com.klef.ms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tour_packagers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long toutPackageId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String type;
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String location;
    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
	

	

}
