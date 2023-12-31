package org.JTravels.Reservation_Api.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column (name = "depature_time")
	private String deptime;
	@Column(nullable = false,unique = true)
	private String busnum;
	@Column(nullable = false,name="from_loc")
	private String from;
	@Column(nullable = false, name="to_loc")
	private String to;
	@Column(nullable = false,name="date_of_registeration")
	private LocalDate dor;
	@Column(nullable = false,name="number_of_seats" )
	private int nos;
	@Column (name = "destination_time")
	private String destime;
	private String journey_hrs;
	private String category;
	private String imageurl;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Admin admin;
	@OneToMany(mappedBy = "bus")
	private List<Ticket> tickets;

}
