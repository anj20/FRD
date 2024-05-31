package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.respositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import com.lcwd.hotel.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        Hotel resp = redisService.get(id,Hotel.class);
        if (resp == null) {
            Hotel body=hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
            redisService.set(id,body,300l);
            return body;
        }
        else {
            return resp;
        }
    }
}
