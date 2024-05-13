package com.mkpits.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

	    public Address(String string, String string2, String string3, String string4) {
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotNull(message = "street is required")
	    private String street;
	    
	    @NotNull(message = "City is required")
	    private String city;
	    
	    @NotNull(message = "state is required")
	    private String state;
	    
	    @NotNull(message = "landmark is required")
	    private String landmark;
	    
	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    @JsonBackReference 
	    private Employee employee;
	    
}
