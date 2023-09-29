package org.JTravels.Reservation_Api.service;

import java.util.List;
import java.util.Optional;

import org.JTravels.Reservation_Api.Dto.Bus;
import org.JTravels.Reservation_Api.Dto.ResponseStructure;
import org.JTravels.Reservation_Api.Dto.Slots;
import org.JTravels.Reservation_Api.Dto.Ticket;
import org.JTravels.Reservation_Api.Dto.User;
import org.JTravels.Reservation_Api.Exception.IdNotFoundException;
import org.JTravels.Reservation_Api.dao.BusDao;
import org.JTravels.Reservation_Api.dao.TicketDao;
import org.JTravels.Reservation_Api.dao.UserDao;
import org.aspectj.weaver.patterns.ConcreteCflowPointcut.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	@Autowired
	private TicketDao dao;
	@Autowired
	private UserDao udao;
	@Autowired
	private BusDao bdao;
	public ResponseEntity<ResponseStructure<Ticket>> BookTicket(int user_id,int bus_id,Ticket t){
		ResponseStructure<Ticket> res=new ResponseStructure<>();
		Optional<User> opu=udao.findbyid(user_id);
		Optional<Bus> opb=bdao.findbyID(bus_id);
		if(opu.isPresent() && opb.isPresent()) {
			User u=opu.get();
			Bus b=opb.get();
			u.getTickets().add(t);
			b.getTickets().add(t);
			t.setBus(b);
			t.setUser(u);
			List<Slots> ls=t.getSlots();
			for(Slots s : ls) {
				s.setTicket(t);
			}
			udao.Update(u);
			bdao.update(b);
			dao.save(t);
			
			//structure creation
			res.setData(t);
			res.setHttpstatus(HttpStatus.CREATED.value());
			res.setMessage("ticket booked");
			return new ResponseEntity<ResponseStructure<Ticket>>(res,HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
     public ResponseEntity<ResponseStructure<String>> cancel(int id){
    	 ResponseStructure<String> res=new ResponseStructure<>();
    	 Optional<Ticket> op=dao.findbyid(id);
    	 if(op.isPresent()) {
    		 dao.cancel(id);
    		 res.setData("Ticket has been canceled");
    		 res.setMessage("Ticket found");
    		 res.setHttpstatus(HttpStatus.OK.value());
    		 return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.OK);
    	 }
    	 throw new IdNotFoundException();
     }
}
