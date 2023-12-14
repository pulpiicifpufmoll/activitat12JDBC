package org.example;

import org.example.Model.Agency;
import org.example.Model.Booking;
import org.example.Model.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLSaxHandler extends DefaultHandler {
    private String content;
    private Booking booking;
    private ArrayList<Booking> bookingList;
    public XMLSaxHandler() {
        this.bookingList = new ArrayList<>();
        this.booking = new Booking();
    }

    public void startDocument() throws SAXException
    {
        System.out.println("Start Document");
    }

    public void startElement(String url,
                             String localName,
                             String qName,
                             Attributes attributes)
            throws  SAXException
    {
        if (qName.equals("booking")){
            booking = new Booking();
            booking.setId(Integer.parseInt(attributes.getValue("location_number")));
        } else if (qName.equals("client")){
            Client client = new Client();
            client.setId(Integer.parseInt(attributes.getValue("id_client")));
            client.setName(getContent());
            booking.setClient(client);
        }else if (qName.equals("agency")){
            Agency agency = new Agency();
            agency.setId(Integer.parseInt(attributes.getValue("id_agency")));
            agency.setName(getContent());
            booking.setAgency(agency);
        }else if (qName.equals("price")){
            //booking.setPrice(vector.getContent());
        }else if (qName.equals("room")){
            //booking.setRoom(vector.getContent());
        }else if (qName.equals("hotel")){
            //booking.setHotel(vector.getContent());
        }else if (qName.equals("check_in")){
            //booking.setCheckIn(vector.getContent());
        }else if (qName.equals("room_nights")){
            //booking.setRoomNights(vector.getContent());
        }
    }

    public void endElement(String url,
                           String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("booking")){
            getBookingList().add(booking);
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException
    {
        setContent(new String(ch, start, length).trim());
    }

    public void endDocument() throws SAXException
    {
        System.out.println("End Document");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
