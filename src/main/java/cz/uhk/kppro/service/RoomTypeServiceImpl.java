package cz.uhk.kppro.service;

import cz.uhk.kppro.model.RoomType;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public  RoomTypeServiceImpl(
            RoomTypeRepository roomTypeRepository
    ){
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType addRoomType(int capacity, int size, double price) {
        RoomType rt = new RoomType();
        rt.setCapacity(capacity);
        rt.setSize(size);
        rt.setPrice(price);
        roomTypeRepository.save(rt);
        return rt;
    }
}
