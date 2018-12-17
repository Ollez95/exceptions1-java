package entities;

import Exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date CheckIn;
    private Date CheckOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(){

    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();
        if(checkIn.before(now) || checkOut.before(now)){
            throw new DomainException("Check-out date must be for future dates");
        }
        if (!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.CheckIn = checkIn;
        this.CheckOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return CheckIn;
    }

    public Date getCheckOut() {
        return CheckOut;
    }

    public long duration(){
        long diff = CheckOut.getTime() - getCheckIn().getTime();
        return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);


    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException{

        Date now = new Date();

        if(checkIn.before(now) || checkOut.before(now)){
            throw new DomainException("Check-out date must be for future dates");
        }
       if (!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }

        this.CheckIn = checkIn;
        this.CheckOut = checkOut;


    }

    @Override
    public String toString(){

        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(CheckIn)
                + ", check-out "
                + sdf.format(CheckOut)
                + ", "
                + duration()
                + " nights";
    }


}
