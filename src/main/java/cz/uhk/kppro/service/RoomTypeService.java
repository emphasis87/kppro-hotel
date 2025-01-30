package cz.uhk.kppro.service;

import cz.uhk.kppro.model.RoomType;

public interface RoomTypeService {
    RoomType addRoomType(int capacity, int size, double price);
}
